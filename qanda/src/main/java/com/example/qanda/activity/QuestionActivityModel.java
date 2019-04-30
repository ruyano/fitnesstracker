package com.example.qanda.activity;

import com.example.qanda.R;
import com.example.qanda.models.Question.AnswerType;

import androidx.databinding.ObservableField;

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
