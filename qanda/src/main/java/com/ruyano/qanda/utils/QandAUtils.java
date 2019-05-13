package com.ruyano.qanda.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class QandAUtils {

    public static void hideKeyboard(View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    public static String getStringFromCalendar(Calendar calendar) {
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, new Locale("pt","BR"));
        return sdf.format(calendar.getTime());
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
