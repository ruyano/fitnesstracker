package br.com.fitnesstracker.util;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CustomViewModel extends ViewModel {

    protected ObservableBoolean isLoading;
    protected MutableLiveData<Boolean> hideKeyboard;

    public CustomViewModel() {
        isLoading = new ObservableBoolean(false);
        hideKeyboard = new MutableLiveData<>();
    }

    public ObservableBoolean getIsLoading() {
        return isLoading;
    }

    public void setIsLoading(Boolean isLoading) {
        this.isLoading.set(isLoading);
    }

    public MutableLiveData<Boolean> getHideKeyboard() {
        return hideKeyboard;
    }

}
