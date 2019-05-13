package com.ruyano.qanda.activity;

import androidx.databinding.ObservableField;

import com.ruyano.qanda.R;
import com.ruyano.qanda.models.Question.AnswerType;

public class QuestionActivityModel {

    public ObservableField<String> question = new ObservableField<>();
    public ObservableField<String> answer = new ObservableField<>();
    public ObservableField<AnswerType> answerType = new ObservableField<>();
    public ObservableField<Integer> answerError = new ObservableField<>();
    public ObservableField<Boolean> prevButtonVisible = new ObservableField<>();
    public ObservableField<Integer> nextButtonText = new ObservableField<>();

    public Boolean isAnswerValid(Boolean showError) {
        String answerValue = answer.get();
        Boolean isAnswerValid = answerValue != null && !answerValue.isEmpty() && !answerValue.equals("0.00");
        if (!isAnswerValid && showError)
            answerError.set(R.string.invalid_answer_error);
        return isAnswerValid;
    }

}
