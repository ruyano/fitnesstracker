package com.example.qanda.activity;

import com.example.qanda.models.Question;

import java.util.ArrayList;

import androidx.lifecycle.ViewModel;

public class QuestionActivityViewModel extends ViewModel {

    private QuestionActivityModel mModel;

    private ArrayList<Question> mQuestions;
    private Integer mCurrentQuestionPosition;

    public void init(ArrayList<Question> questions) {
        mQuestions = questions;
        mCurrentQuestionPosition = 0;
        mModel = new QuestionActivityModel();
        mModel.prevButtonVisible.set(false);
        mModel.nextButtonVisible.set(true);

        if (mQuestions != null && mQuestions.size() > 0)
            setQuestion();
    }

    public QuestionActivityModel getModel() {
        return mModel;
    }

    private void setQuestion() {
        Question currentQuestion = mQuestions.get(mCurrentQuestionPosition);
        mModel.question.set(currentQuestion.getQuestion());
        mModel.answerType.set(currentQuestion.getAnswerType());
    }

    public void prevBtnClick() {
        if (mCurrentQuestionPosition > 0) {
            mCurrentQuestionPosition--;
            setQuestion();
        }
        updateButtonsVisibility();
        mModel.answer.set("");
    }

    public void nextBtnClick() {
        if (mModel.isAnswerValid()) {
            if (mCurrentQuestionPosition < mQuestions.size() - 1) {
                mCurrentQuestionPosition++;
                setQuestion();
            }
            updateButtonsVisibility();
            mModel.answer.set("");
        }
    }

    private void updateButtonsVisibility() {
        if (mCurrentQuestionPosition == 0) {
            showNextButtons();
            return;
        }

        if (mCurrentQuestionPosition == mQuestions.size() -1) {
            showPrevButton();
            return;
        }

        showPrevAndNextButtons();
    }

    private void showPrevAndNextButtons() {
        mModel.prevButtonVisible.set(true);
        mModel.nextButtonVisible.set(true);
    }

    private void showPrevButton() {
        mModel.prevButtonVisible.set(true);
        mModel.nextButtonVisible.set(false);
    }

    private void showNextButtons() {
        mModel.prevButtonVisible.set(false);
        mModel.nextButtonVisible.set(true);
    }

}
