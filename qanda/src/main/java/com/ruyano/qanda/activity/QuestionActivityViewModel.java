package com.ruyano.qanda.activity;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ruyano.qanda.R;
import com.ruyano.qanda.models.Question;
import com.ruyano.qanda.utils.QandAUtils;

import java.util.ArrayList;
import java.util.Calendar;

public class QuestionActivityViewModel extends ViewModel {

    private QuestionActivityModel mModel;
    private ArrayList<Question> mQuestions;
    private Integer mCurrentQuestionPosition;
    private MutableLiveData<Boolean> cancelQAndA;
    private MutableLiveData<Boolean> completeQAndA;

    public void init(ArrayList<Question> questions) {
        cancelQAndA = new MutableLiveData<>();
        completeQAndA = new MutableLiveData<>();
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

        if (currentQuestion.getAnswerType() == Question.AnswerType.DATE
                && currentQuestion.getAnswer() == null) {
            mModel.answer.set(QandAUtils.getStringFromCalendar(Calendar.getInstance()));
        } else {
            mModel.answer.set(currentQuestion.getAnswer());
        }
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
                completeQAndA();
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


        cancelQAndA();
    }

    private void cancelQAndA() {
        cancelQAndA.setValue(cancelQAndA.getValue() == null ||
                !cancelQAndA.getValue());
    }

    private void completeQAndA() {
        completeQAndA.setValue(completeQAndA.getValue() == null ||
                !completeQAndA.getValue());
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

    public MutableLiveData<Boolean> getCancelQAndA() {
        return cancelQAndA;
    }

    public MutableLiveData<Boolean> getCompleteQAndA() {
        return completeQAndA;
    }
}
