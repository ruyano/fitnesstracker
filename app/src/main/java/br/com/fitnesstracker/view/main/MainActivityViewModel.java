package br.com.fitnesstracker.view.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;

import br.com.fitnesstracker.models.Preferences;
import br.com.fitnesstracker.repositories.preferences.PreferencesRepository;
import br.com.fitnesstracker.repositories.preferences.PreferencesRepositoryImpl;

public class MainActivityViewModel extends ViewModel {

    private MainActivityModel mModel;
    private PreferencesRepository mPreferencesRepository;

    public void init() {
        mModel = new MainActivityModel();
        mPreferencesRepository = new PreferencesRepositoryImpl();
    }

    public MainActivityModel getModel() {
        return mModel;
    }

    public void setFabButtonVisible(Boolean isFabButtonVisible) {
        mModel.fabButtonVisible.set(isFabButtonVisible);
    }

    public void getPreferences() {
        mPreferencesRepository.readPreferencesForUser(FirebaseAuth.getInstance().getUid());
    }

    public MutableLiveData<Preferences> getPreferencesLiveData() {
        return mPreferencesRepository.getPreferencesLiveData();
    }

}
