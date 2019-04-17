package br.com.fitnesstracker.repositories.user.management;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

public class UserManagementRepositoryImpl implements UserManagementRepository {

    private MutableLiveData<Boolean> mLoginLiveData;
    private MutableLiveData<Boolean> mSignUpLiveData;
    private MutableLiveData<Boolean> mResetPassWordLiveData;

    @Override
    public void login(String email, String password) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                mLoginLiveData.setValue(task.isSuccessful());
            }
        });
    }

    @Override
    public MutableLiveData<Boolean> getLoginLiveData() {
        if (mLoginLiveData == null)
            mLoginLiveData = new MutableLiveData<>();
        return mLoginLiveData;
    }

    @Override
    public void signUp(String email, String password) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                mSignUpLiveData.postValue(task.isSuccessful());
            }
        });
    }

    @Override
    public MutableLiveData<Boolean> getSignUpLiveData() {
        if (mSignUpLiveData == null)
            mSignUpLiveData = new MutableLiveData<>();
        return mSignUpLiveData;
    }

    @Override
    public void resetPassWord(String email) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                mResetPassWordLiveData.postValue(task.isSuccessful());
            }
        });
    }

    @Override
    public MutableLiveData<Boolean> getResetPassWordLiveData() {
        if (mResetPassWordLiveData == null)
            mResetPassWordLiveData = new MutableLiveData<>();
        return mResetPassWordLiveData;
    }

}
