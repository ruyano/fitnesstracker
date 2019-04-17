package br.com.fitnesstracker.view.resetpassword;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import br.com.fitnesstracker.R;
import br.com.fitnesstracker.databinding.ActivityResetPasswordBinding;
import br.com.fitnesstracker.util.AppUtil;

public class ResetPasswordActivity extends AppCompatActivity {

    public static Intent getIntent(Context context) {
        return new Intent(context, ResetPasswordActivity.class);
    }

    private ResetPasswordViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

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

        setupResetPasswordObserver();
    }

    private void setupResetPasswordObserver() {
        mViewModel.getResetPassWordLiveData().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean resetPasswordSuccess) {
                mViewModel.setIsLoading(false);
                if (resetPasswordSuccess) {
                    Toast.makeText(ResetPasswordActivity.this, getString(R.string.reset_password_success), Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(ResetPasswordActivity.this, getString(R.string.reset_password_error), Toast.LENGTH_SHORT).show();
                }
            }
        });

        mViewModel.getHideKeyboard().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                AppUtil.hideKeyboard(getCurrentFocus());
            }
        });
    }
}
