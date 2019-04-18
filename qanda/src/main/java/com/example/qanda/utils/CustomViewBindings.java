package com.example.qanda.utils;

import android.graphics.Typeface;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import com.example.qanda.models.Question.AnswerType;
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


    @BindingAdapter("visibilityBool")
    public static void bindVisibilityBool(View view, Boolean isVisible) {
        view.setVisibility(isVisible == null ? View.VISIBLE : isVisible ? View.VISIBLE : View.INVISIBLE);
    }

    @BindingAdapter("answerType")
    public static void bindanswerType(EditText editText, AnswerType answerType) {
        switch (answerType) {
            case DATE:
                editText.setInputType(InputType.TYPE_CLASS_DATETIME);
                break;
            case DOUBLE:
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                break;
            case STRING:
                editText.setInputType(InputType.TYPE_CLASS_TEXT);
                break;
            default:
                break;
        }
    }

}
