package br.com.fitnesstracker.view.login;

import android.view.View;
import android.widget.EditText;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {

    private LoginModel loginModel;
    private View.OnFocusChangeListener focusChangeEmail;
    private View.OnFocusChangeListener focusChangePassword;
    private MutableLiveData<LoginModel> loginBtnClick = new MutableLiveData<>();

    void init() {
        loginModel = new LoginModel();

        focusChangeEmail = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (v instanceof EditText) {
                    EditText et = (EditText) v;
                    if (et.getText().length() > 0 && !hasFocus) {
                        loginModel.isEmailValid(true);
                    }
                }
            }
        };

        focusChangePassword = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (v instanceof EditText) {
                    EditText et = (EditText) v;
                    if (et.getText().length() > 0 && !hasFocus) {
                        loginModel.isPasswordValid(true);
                    }
                }
            }
        };
    }

    public LoginModel getLoginModel() {
        return loginModel;
    }

    public View.OnFocusChangeListener getFocusChangeEmail() {
        return focusChangeEmail;
    }

    public View.OnFocusChangeListener getFocusChangePassword() {
        return focusChangePassword;
    }

    public MutableLiveData<LoginModel> getLoginBtnClick() {
        return loginBtnClick;
    }

    public void doLogin() {
        if (loginModel.isValid()) {
            loginBtnClick.setValue(loginModel);
        }
    }

}
