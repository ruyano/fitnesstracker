package br.com.fitnesstracker.view.login;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableField;
import br.com.fitnesstracker.BR;
import br.com.fitnesstracker.R;
import br.com.fitnesstracker.util.ValidationUtils;

public class LoginModel extends BaseObservable {

    private String email;
    private String password;
    public ObservableField<Integer> emailError = new ObservableField<>();
    public ObservableField<Integer> passwordError = new ObservableField<>();

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.valid);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.valid);
    }

    @Bindable
    public boolean isValid() {
        Boolean emailValid = isEmailValid(false);
        Boolean passwordValid = isPasswordValid(false);
        return emailValid && passwordValid;
    }

    public boolean isEmailValid(boolean setMessage) {
        // Minimum a@b.c
        if (email != null && email.length() > 5) {
            if (ValidationUtils.isEmailValid(email)) {
                emailError.set(null);
                return true;
            }
            if (setMessage)
                emailError.set(R.string.error_format_invalid);
            return false;

        }
        if (setMessage)
            emailError.set(R.string.error_too_short);
        return false;
    }

    public boolean isPasswordValid(boolean setMessage) {
        if (password != null && password.length() > 5) {
            passwordError.set(null);
            return true;
        } else {
            if (setMessage)
                passwordError.set(R.string.error_too_short);
            return false;
        }
    }

}
