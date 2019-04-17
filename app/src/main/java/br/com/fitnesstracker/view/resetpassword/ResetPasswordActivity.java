package br.com.fitnesstracker.view.resetpassword;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import br.com.fitnesstracker.R;
import br.com.fitnesstracker.databinding.ActivityResetPasswordBinding;

public class ResetPasswordActivity extends AppCompatActivity {

    public static Intent getIntent(Context context) {
        return new Intent(context, ResetPasswordActivity.class);
    }

    private ResetPasswordViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        findViewById(R.id.et_email).requestFocus();

        setupBindings(savedInstanceState);
    }

    public void backButton(View view) {
        finish();
    }

    private void setupBindings(Bundle savedInstanceState) {
        ActivityResetPasswordBinding activityResetPasswordBinding = DataBindingUtil.setContentView(this, R.layout.activity_reset_password);
        mViewModel = ViewModelProviders.of(this).get(ResetPasswordViewModel.class);
        if (savedInstanceState == null)
            mViewModel.init();
        activityResetPasswordBinding.setViewModel(mViewModel);
    }
}
