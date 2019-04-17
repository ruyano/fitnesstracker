package br.com.fitnesstracker.util;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class AppUtil {

    public static void hideKeyboard(View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

}
