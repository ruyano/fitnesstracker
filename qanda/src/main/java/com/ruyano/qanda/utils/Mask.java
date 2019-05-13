package com.ruyano.qanda.utils;

import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.widget.EditText;

public class Mask  implements TextWatcher {

    private EditText mEditText;
    private boolean isRunning = false;

    public Mask(EditText editText) {
        this.mEditText = editText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (isRunning)
            return;

        isRunning = true;

        if (!s.toString().matches("^\\$(\\d{1,3}(\\,\\d{3})*|(\\d+))(\\.\\d{2})?$")) {
            String userInput = "" + s.toString().replaceAll("[^\\d]", "");
            StringBuilder numberBuilder = new StringBuilder(userInput);

            while (numberBuilder.length() > 3 && numberBuilder.charAt(0) == '0') {
                numberBuilder.deleteCharAt(0);
            }
            while (numberBuilder.length() < 3) {
                numberBuilder.insert(0, '0');
            }
            numberBuilder.insert(numberBuilder.length() - 2, '.');

            mEditText.setText(numberBuilder.toString());
            Selection.setSelection(mEditText.getText(), mEditText.getText().length());
        }
        isRunning = false;
    }

    @Override
    public void afterTextChanged(Editable s) { }
}
