package com.example.qanda.activity;

import com.example.qanda.R;
import com.example.qanda.models.Question;

import java.util.ArrayList;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class QuestionActivityViewModel extends ViewModel {

    private QuestionActivityModel mModel;
    private ArrayList<Question> mQuestions;
    private Integer mCurrentQuestionPosition;
    private MutableLiveData<Boolean> finishActivityObserver;

    public void init(ArrayList<Question> questions) {
        finishActivityObserver = new MutableLiveData<>();
        mQuestions = questions;
        mCurrentQuestionPosition = 0;
        mModel = new QuestionActivityModel();
        mModel.prevButtonVisible.set(false);
        mModel.nextButtonText.set(R.string.next);

        if (mQuestions != null && mQuestions.size() > 0)
            setupQuestion();
    }

    public QuestionActivityModel getModel() {
        return mModel;
    }

    private void setupQuestion() {
        Question currentQuestion = mQuestions.get(mCurrentQuestionPosition);
        mModel.answerError.set(null);
        mModel.question.set(currentQuestion.getQuestion());
        mModel.answerType.set(currentQuestion.getAnswerType());
        mModel.answer.set(currentQuestion.getAnswer());
    }

    public void prevBtnClick() {
        if (mModel.isAnswerValid(false))
            mQuestions.get(mCurrentQuestionPosition).setAnswer(mModel.answer.get());
        if (mCurrentQuestionPosition > 0) {
            mCurrentQuestionPosition--;
            setupQuestion();
        }
        updateButtonsVisibility();
    }

    public void nextBtnClick() {
        if (mModel.isAnswerValid(true)) {
            mQuestions.get(mCurrentQuestionPosition).setAnswer(mModel.answer.get());
            if (mCurrentQuestionPosition == mQuestions.size() -1) {
                finish();
                return;
            }
            if (mCurrentQuestionPosition < mQuestions.size() - 1) {
                mCurrentQuestionPosition++;
                setupQuestion();
            }
            updateButtonsVisibility();
        }
    }

    public void onBackPressed() {
        if (mCurrentQuestionPosition > 0) {
            prevBtnClick();
            return;
        }
        finish();
    }

    private void finish() {
        finishActivityObserver.setValue(finishActivityObserver.getValue() == null ||
                !finishActivityObserver.getValue());
    }

    public Boolean hasFinishedQAndA() {
        return mCurrentQuestionPosition == mQuestions.size() -1;
    }

    public ArrayList<Question> getQuestions() {
        return mQuestions;
    }

    private void updateButtonsVisibility() {
        if (mCurrentQuestionPosition == 0) {
            mModel.prevButtonVisible.set(false);
            mModel.nextButtonText.set(R.string.next);
            return;
        }

        if (mCurrentQuestionPosition == mQuestions.size() -1) {
            mModel.prevButtonVisible.set(true);
            mModel.nextButtonText.set(R.string.finish);
            return;
        }

        mModel.prevButtonVisible.set(true);
        mModel.nextButtonText.set(R.string.next);
    }

    public MutableLiveData<Boolean> getFinishActivityObserver() {
        return finishActivityObserver;
    }
}
