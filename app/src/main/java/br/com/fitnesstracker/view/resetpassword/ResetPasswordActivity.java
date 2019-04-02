package br.com.fitnesstracker.view.resetpassword;

import androidx.appcompat.app.AppCompatActivity;
import br.com.fitnesstracker.R;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ResetPasswordActivity extends AppCompatActivity {

    public static Intent getIntent(Context context) {
        return new Intent(context, ResetPasswordActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        findViewById(R.id.et_email).requestFocus();
    }

    public void backButton(View view) {
        finish();
    }
}
