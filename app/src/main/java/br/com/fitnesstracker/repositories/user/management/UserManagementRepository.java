package br.com.fitnesstracker.repositories.user.management;

import androidx.lifecycle.MutableLiveData;

public interface UserManagementRepository {

    void login(String email, String password);

    MutableLiveData<Boolean> getLoginLiveData();

    void signUp(String email, String password);

    MutableLiveData<Boolean> getSignUpLiveData();

    void resetPassWord(String email);

    MutableLiveData<Boolean> getResetPassWordLiveData();

}
