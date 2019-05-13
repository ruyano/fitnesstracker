package com.ruyano.qanda.utils;

import android.app.DatePickerDialog;
import android.content.Context;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.databinding.BindingAdapter;

import com.google.android.material.textfield.TextInputLayout;
import com.ruyano.qanda.models.Question.AnswerType;

import java.util.Calendar;

public class CustomViewBindings {

    private static Mask mask;

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
    public static void bindanswerType(final EditText editText, AnswerType answerType) {
        if (mask != null) {
            editText.removeTextChangedListener(mask);
            mask = null;
        }
        InputMethodManager imm = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        switch (answerType) {
            case DATE:
                editText.setInputType(InputType.TYPE_NULL);
                editText.setOnClickListener(getOnClickListener(editText));
                QandAUtils.hideKeyboard(editText);
                break;
            case DOUBLE:
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                editText.setOnFocusChangeListener(null);
                editText.setOnClickListener(null);
                mask = new Mask(editText);
                editText.addTextChangedListener(mask);
                imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
                editText.requestFocus();
                break;
            case STRING:
                editText.setInputType(InputType.TYPE_CLASS_TEXT);
                editText.setOnFocusChangeListener(null);
                editText.setOnClickListener(null);
                imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
                editText.requestFocus();
                break;
            default:
                break;
        }
    }

    private static View.OnFocusChangeListener getDataListener(final EditText editText) {
        return new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showDataPikerDialog(editText);
                }
            }
        };
    }

    private static View.OnClickListener getOnClickListener(final EditText editText) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDataPikerDialog(editText);
            }
        };
    }

    private static void showDataPikerDialog(final EditText editText) {
        final Calendar myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                editText.setText(QandAUtils.getStringFromCalendar(myCalendar));
            }
        };

        new DatePickerDialog(editText.getContext(), date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

}
