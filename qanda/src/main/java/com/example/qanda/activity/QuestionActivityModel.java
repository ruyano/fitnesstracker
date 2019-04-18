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
    public ObservableField<Boolean> nextButtonVisible = new ObservableField<>();

    public Boolean isAnswerValid() {
        String answerValue = answer.get();
        Boolean isAnswerValid = answerValue != null && !answerValue.isEmpty();
        if (!isAnswerValid)
            answerError.set(R.string.empty_answer_error);
        return isAnswerValid;
    }

}
