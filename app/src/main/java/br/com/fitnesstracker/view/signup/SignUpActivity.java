package br.com.fitnesstracker.view.signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import br.com.fitnesstracker.R;
import br.com.fitnesstracker.databinding.ActivitySignUpBinding;
import br.com.fitnesstracker.view.login.LoginModel;
import br.com.fitnesstracker.view.resetpassword.ResetPasswordActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

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
        mViewModel.getSignUpBtnClick().observe(this, new Observer<LoginModel>() {
            @Override
            public void onChanged(LoginModel loginModel) {
                FirebaseAuth auth = FirebaseAuth.getInstance();
                auth.createUserWithEmailAndPassword(loginModel.getEmail(), loginModel.getPassword())
                        .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(SignUpActivity.this, "Sucesso!", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                Toast.makeText(SignUpActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    public void backButton(View view) {
        finish();
    }

}
