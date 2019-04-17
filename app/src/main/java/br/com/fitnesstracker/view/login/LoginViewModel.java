package br.com.fitnesstracker.view.login;

import android.view.View;
import android.widget.EditText;

import androidx.lifecycle.MutableLiveData;
import br.com.fitnesstracker.repositories.user.management.UserManagementRepository;
import br.com.fitnesstracker.repositories.user.management.UserManagementRepositoryImpl;
import br.com.fitnesstracker.util.CustomViewModel;

public class LoginViewModel extends CustomViewModel {

    private LoginModel loginModel;
    private View.OnFocusChangeListener focusChangeEmail;
    private View.OnFocusChangeListener focusChangePassword;
    private UserManagementRepository userManagementRepository;

    void init() {
        userManagementRepository = new UserManagementRepositoryImpl();
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

    public MutableLiveData<Boolean> getLoginLiveData() {
        return userManagementRepository.getLoginLiveData();
    }

    public void doLogin() {
        if (loginModel.isValid()) {
            hideKeyboard.setValue(hideKeyboard.getValue() == null || !hideKeyboard.getValue());
            isLoading.set(true);
            userManagementRepository.login(loginModel.getEmail(), loginModel.getPassword());
        }
    }


}
