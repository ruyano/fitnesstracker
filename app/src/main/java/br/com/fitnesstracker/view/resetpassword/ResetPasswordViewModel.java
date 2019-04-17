package br.com.fitnesstracker.view.resetpassword;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

public class ResetPasswordViewModel extends ViewModel {

    public void init() {

    }

    public void requestResetPassword() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.sendPasswordResetEmail("ruyano@gmail.com").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    // TODO - mensagem de sucesso.
                    return;
                }
                // TODO - mensagem de erro.
            }
        });
    }

}
