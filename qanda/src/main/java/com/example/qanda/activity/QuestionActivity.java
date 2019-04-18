package com.example.qanda.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.qanda.utils.QAndA;
import com.example.qanda.models.Question;
import com.example.qanda.R;
import com.example.qanda.databinding.ActivityQuestionBinding;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

public class QuestionActivity extends AppCompatActivity {

    private static final String QUESTIONS_LIST = "QUESTIONS_LIST";

    public static void startQAndA(Activity activity, ArrayList<Question> questions) {
        Intent intent = new Intent(activity, QuestionActivity.class);
        intent.putParcelableArrayListExtra(QUESTIONS_LIST, questions);
        activity.startActivityForResult(intent, QAndA.QANDA_REQUEST_CODE);
    }

    private QuestionActivityViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        setupBindings(savedInstanceState);
    }

    private void setupBindings(Bundle savedInstanceState) {
        ActivityQuestionBinding activityQuestionBinding = DataBindingUtil.setContentView(this, R.layout.activity_question);
        mViewModel = ViewModelProviders.of(this).get(QuestionActivityViewModel.class);
        if (savedInstanceState == null)
            mViewModel.init(getQuestionsExtra());
        activityQuestionBinding.setViewModel(mViewModel);
    }

    private ArrayList<Question> getQuestionsExtra() {
        if (getIntent().getExtras().getParcelableArrayList(QUESTIONS_LIST) != null) {
            return getIntent().getExtras().getParcelableArrayList(QUESTIONS_LIST);
        }
        return null;
    }

    public void backButton(View view) {
        onBackPressed();
        // TODO -
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
