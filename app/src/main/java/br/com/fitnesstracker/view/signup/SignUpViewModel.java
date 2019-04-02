package br.com.fitnesstracker.view.signup;

import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import br.com.fitnesstracker.view.login.LoginModel;

public class SignUpViewModel extends ViewModel {

    private LoginModel mLoginModel;
    private View.OnFocusChangeListener mFocusChangeEmail;
    private View.OnFocusChangeListener mFocusChangePassword;
    private MutableLiveData<LoginModel> mSignUpBtnClick = new MutableLiveData<>();

    public void init() {
        mLoginModel = new LoginModel();

        mFocusChangeEmail = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (v instanceof EditText) {
                    EditText et = (EditText) v;
                    if (et.getText().length() > 0 && !hasFocus) {
                        mLoginModel.isEmailValid(true);
                    }
                }
            }
        };

        mFocusChangePassword = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (v instanceof EditText) {
                    EditText et = (EditText) v;
                    if (et.getText().length() > 0 && !hasFocus) {
                        mLoginModel.isPasswordValid(true);
                    }
                }
            }
        };
    }

    public LoginModel getLoginModel() {
        return mLoginModel;
    }

    public View.OnFocusChangeListener getFocusChangeEmail() {
        return mFocusChangeEmail;
    }

    public View.OnFocusChangeListener getFocusChangePassword() {
        return mFocusChangePassword;
    }

    public MutableLiveData<LoginModel> getSignUpBtnClick() {
        return mSignUpBtnClick;
    }

    public void doSignUp() {
        if (mLoginModel.isValid()) {
            mSignUpBtnClick.setValue(mLoginModel);
        }
    }
}
