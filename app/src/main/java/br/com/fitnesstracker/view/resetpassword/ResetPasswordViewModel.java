package br.com.fitnesstracker.view.resetpassword;

import android.view.View;
import android.widget.EditText;

import androidx.lifecycle.MutableLiveData;
import br.com.fitnesstracker.repositories.user.management.UserManagementRepository;
import br.com.fitnesstracker.repositories.user.management.UserManagementRepositoryImpl;
import br.com.fitnesstracker.util.CustomViewModel;
import br.com.fitnesstracker.view.login.LoginModel;

public class ResetPasswordViewModel extends CustomViewModel {

    private LoginModel mLoginModel;
    private View.OnFocusChangeListener mFocusChangeEmail;
    private UserManagementRepository userManagementRepository;

    public void init() {
        userManagementRepository = new UserManagementRepositoryImpl();
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
    }

    public LoginModel getLoginModel() {
        return mLoginModel;
    }

    public View.OnFocusChangeListener getFocusChangeEmail() {
        return mFocusChangeEmail;
    }

    public MutableLiveData<Boolean> getResetPassWordLiveData() {
        return userManagementRepository.getResetPassWordLiveData();
    }

    public void requestResetPassword() {
        if (mLoginModel.isEmailValid(true)) {
            hideKeyboard.setValue(hideKeyboard.getValue() == null || !hideKeyboard.getValue());
            isLoading.set(true);
            userManagementRepository.resetPassWord(mLoginModel.getEmail());
        }
    }

}
