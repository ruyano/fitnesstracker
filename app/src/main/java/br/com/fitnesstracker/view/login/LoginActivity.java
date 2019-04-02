package br.com.fitnesstracker.view.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import br.com.fitnesstracker.MainActivity;
import br.com.fitnesstracker.R;
import br.com.fitnesstracker.databinding.ActivityLoginBinding;
import br.com.fitnesstracker.view.resetpassword.ResetPasswordActivity;
import br.com.fitnesstracker.view.signup.SignUpActivity;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            goToMain();
        }

        setupBindings(savedInstanceState);

    }

    private void setupBindings(Bundle savedInstanceState) {
        ActivityLoginBinding activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        mViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        if (savedInstanceState == null)
            mViewModel.init();
        activityLoginBinding.setViewModel(mViewModel);

        setupLoginBtn();
    }

    private void setupLoginBtn() {
        mViewModel.getLoginBtnClick().observe(this, new Observer<LoginModel>() {
            @Override
            public void onChanged(LoginModel loginModel) {
                FirebaseAuth auth = FirebaseAuth.getInstance();
                auth.signInWithEmailAndPassword(loginModel.getEmail(), loginModel.getPassword())
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    goToMain();
                                    return;
                                }
                                Toast.makeText(LoginActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    public void goToSignUp(View view) {
        startActivity(SignUpActivity.getIntent(this));
    }

    public void goToResetPassword(View view) {
        startActivity(ResetPasswordActivity.getIntent(this));
    }

    private void goToMain() {
        startActivity(MainActivity.getIntent(this));
        finish();
        return;
    }
}
