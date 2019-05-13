package com.ruyano.qanda.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.ruyano.qanda.R;
import com.ruyano.qanda.databinding.ActivityQuestionBinding;
import com.ruyano.qanda.models.Question;
import com.ruyano.qanda.utils.QAndA;
import com.ruyano.qanda.utils.QandAUtils;

import java.util.ArrayList;

import static com.ruyano.qanda.utils.QAndA.QUESTIONS_LIST;

public class QuestionActivity extends AppCompatActivity {

    public static void startQAndA(Activity activity, ArrayList<Question> questions) {
        Intent intent = new Intent(activity, QuestionActivity.class);
        intent.putParcelableArrayListExtra(QUESTIONS_LIST, questions);
        activity.startActivityForResult(intent, QAndA.QANDA_REQUEST_CODE);
    }

    public static void startQAndA(Fragment fragment, ArrayList<Question> questions) {
        Intent intent = new Intent(fragment.getActivity(), QuestionActivity.class);
        intent.putParcelableArrayListExtra(QUESTIONS_LIST, questions);
        fragment.startActivityForResult(intent, QAndA.QANDA_REQUEST_CODE);
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
        setupOnBackPressedObserver();
    }

    private void setupOnBackPressedObserver() {
        mViewModel.getCancelQAndA().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                QandAUtils.showAlertDialog(QuestionActivity.this,
                        getString(R.string.atention),
                        getString(R.string.back_button_message),
                        getString(R.string.yes),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                setResultForActivity();
                                finish();
                            }
                        },
                        getString(R.string.no),
                        null);
            }
        });

        mViewModel.getCompleteQAndA().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                setResultForActivity();
                finish();
            }
        });
    }

    private void setResultForActivity() {
        if (mViewModel.hasFinishedQAndA()) {
            setResult(RESULT_OK,
                    new Intent().putExtra(QUESTIONS_LIST, mViewModel.getQuestions()));
        } else {
            setResult(RESULT_CANCELED);
        }
    }

    private ArrayList<Question> getQuestionsExtra() {
        if (getIntent().getExtras().getParcelableArrayList(QUESTIONS_LIST) != null) {
            return getIntent().getExtras().getParcelableArrayList(QUESTIONS_LIST);
        }
        return null;
    }

    @Override
    public void onBackPressed() {
        mViewModel.onBackPressed();
    }
}
