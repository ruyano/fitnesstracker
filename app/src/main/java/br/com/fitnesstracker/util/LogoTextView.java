package br.com.fitnesstracker.util;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

import static android.util.TypedValue.COMPLEX_UNIT_SP;

public class LogoTextView extends TextView {


    public LogoTextView(Context context) {
        super(context);
        setFont();
    }

    public LogoTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setFont();
    }

    public LogoTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFont();
    }

    private void setFont() {
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "fonts/KaushanScript-Regular.ttf");
        setTypeface(font, Typeface.NORMAL);
        setTextSize(COMPLEX_UNIT_SP, 40);
    }
}
