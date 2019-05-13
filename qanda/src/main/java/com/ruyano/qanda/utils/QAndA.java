package com.ruyano.qanda.utils;

import android.app.Activity;

import androidx.fragment.app.Fragment;

import com.ruyano.qanda.activity.QuestionActivity;
import com.ruyano.qanda.models.Question;

import java.util.ArrayList;

public class QAndA {

    public static final Integer QANDA_REQUEST_CODE = 1919;
    public static final String QUESTIONS_LIST = "QUESTIONS_LIST";

    public static void startQAndA(Activity activity, ArrayList<Question> questions) {
        QuestionActivity.startQAndA(activity, questions);
    }

    public static void startQAndA(Fragment fragment, ArrayList<Question> questions) {
        QuestionActivity.startQAndA(fragment, questions);
    }

}
