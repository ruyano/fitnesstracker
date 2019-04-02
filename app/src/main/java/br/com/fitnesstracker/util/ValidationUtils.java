package br.com.fitnesstracker.util;

import android.util.Patterns;

public class ValidationUtils {

    public static boolean isEmailValid(String email) {
        CharSequence inputStr = email;
        return Patterns.EMAIL_ADDRESS.matcher(inputStr).matches();
    }
}
