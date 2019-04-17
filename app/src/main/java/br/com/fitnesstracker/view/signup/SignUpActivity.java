package br.com.fitnesstracker.view.signup;

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
import br.com.fitnesstracker.databinding.ActivitySignUpBinding;
import br.com.fitnesstracker.view.main.MainActivity;

public class SignUpActivity extends AppCompatActivity {

    public static Intent getIntent(Context context) {
        return new Intent(context, SignUpActivity.class);
    }

    private SignUpViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setupBindings(savedInstanceState);
    }

    private void setupBindings(Bundle savedInstanceState) {
        ActivitySignUpBinding activitySignUpBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);
        mViewModel = ViewModelProviders.of(this).get(SignUpViewModel.class);
        if (savedInstanceState == null)
            mViewModel.init();
        activitySignUpBinding.setViewModel(mViewModel);

        setupSignUpBtn();
    }

    private void setupSignUpBtn() {
        mViewModel.getSignUpLiveData().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean signUpSuccess) {
                if (signUpSuccess) {
                    goToMain();
                } else {
                    Toast.makeText(SignUpActivity.this, getString(R.string.signUpError), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void backButton(View view) {
        finish();
    }

    private void goToMain() {
        startActivity(MainActivity.getIntent(this));
        finish();
        return;
    }

}
