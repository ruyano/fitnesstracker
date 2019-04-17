package br.com.fitnesstracker.util;

import android.view.View;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

import androidx.databinding.BindingAdapter;

public class CustomViewBindings {

    @BindingAdapter("error")
    public static void setError(TextInputLayout textInputLayout, Object strOrResId) {
        if (strOrResId instanceof Integer) {
            textInputLayout.setError(textInputLayout.getContext().getString((Integer) strOrResId));
        } else {
            textInputLayout.setError((String) strOrResId);
        }

    }

    @BindingAdapter("onFocus")
    public static void bindFocusChange(EditText editText, View.OnFocusChangeListener onFocusChangeListener) {
        if (editText.getOnFocusChangeListener() == null) {
            editText.setOnFocusChangeListener(onFocusChangeListener);
        }
    }

    @BindingAdapter("visibilityBool")
    public static void bindVisibilityBool(View view, Boolean isVisible) {
        view.setVisibility(isVisible == null ? View.VISIBLE : isVisible ? View.VISIBLE : View.GONE);
    }

}
