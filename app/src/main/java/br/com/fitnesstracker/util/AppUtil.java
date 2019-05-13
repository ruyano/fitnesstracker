package br.com.fitnesstracker.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.ruyano.qanda.models.Question;

import java.util.ArrayList;

import br.com.fitnesstracker.R;
import br.com.fitnesstracker.models.FisicalAvaliation;

public class AppUtil {

    public static void hideKeyboard(View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    public static FisicalAvaliation getFisicalAvaliationObj(Context context, ArrayList<Question> questions) {
        FisicalAvaliation fisicalAvaliation = new FisicalAvaliation();
        for (Question q : questions) {
            if (q.getQuestion().equals(context.getString(R.string.question_date))) {
                fisicalAvaliation.setDate(q.getAnswer());
            } else if (q.getQuestion().equals(context.getString(R.string.question_neck))) {
                fisicalAvaliation.setNeck(Double.valueOf(q.getAnswer()));
            } else if (q.getQuestion().equals(context.getString(R.string.question_shoulders))) {
                fisicalAvaliation.setShoulders(Double.valueOf(q.getAnswer()));
            } else if (q.getQuestion().equals(context.getString(R.string.question_breastplate))) {
                fisicalAvaliation.setBreastplate(Double.valueOf(q.getAnswer()));
            } else if (q.getQuestion().equals(context.getString(R.string.question_waist))) {
                fisicalAvaliation.setWaist(Double.valueOf(q.getAnswer()));
            } else if (q.getQuestion().equals(context.getString(R.string.question_abdomen))) {
                fisicalAvaliation.setAbdomen(Double.valueOf(q.getAnswer()));
            } else if (q.getQuestion().equals(context.getString(R.string.question_rightArm))) {
                fisicalAvaliation.setRightArm(Double.valueOf(q.getAnswer()));
            } else if (q.getQuestion().equals(context.getString(R.string.question_leftArm))) {
                fisicalAvaliation.setLeftArm(Double.valueOf(q.getAnswer()));
            } else if (q.getQuestion().equals(context.getString(R.string.question_rightLeg))) {
                fisicalAvaliation.setRightLeg(Double.valueOf(q.getAnswer()));
            } else if (q.getQuestion().equals(context.getString(R.string.question_leftLeg))) {
                fisicalAvaliation.setLeftLeg(Double.valueOf(q.getAnswer()));
            } else if (q.getQuestion().equals(context.getString(R.string.question_rightCalf))) {
                fisicalAvaliation.setRightCalf(Double.valueOf(q.getAnswer()));
            } else if (q.getQuestion().equals(context.getString(R.string.question_leftCalf))) {
                fisicalAvaliation.setLeftCalf(Double.valueOf(q.getAnswer()));
            } else if (q.getQuestion().equals(context.getString(R.string.question_weight))) {
                fisicalAvaliation.setWeight(Double.valueOf(q.getAnswer()));
            }
        }

        return fisicalAvaliation;
    }

    public static void showAlertDialog(Context context,
                                       String title,
                                       String message,
                                       String positiveButtonTitle,
                                       DialogInterface.OnClickListener positiveButtonListener,
                                       String negativeButtonTitle,
                                       DialogInterface.OnClickListener negativeButtonListener) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, positiveButtonTitle, positiveButtonListener);
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, negativeButtonTitle, negativeButtonListener);
        alertDialog.show();
    }

}
