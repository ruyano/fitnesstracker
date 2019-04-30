package br.com.fitnesstracker.view.settings;

import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;

import br.com.fitnesstracker.models.Preferences;
import br.com.fitnesstracker.repositories.preferences.PreferencesRepository;
import br.com.fitnesstracker.repositories.preferences.PreferencesRepositoryImpl;

public class SettingsFragmentViewModel extends ViewModel {

    private PreferencesRepository mRepository;

    public void init() {
        mRepository = new PreferencesRepositoryImpl();
    }

    public void updatePreferences(Preferences preferences) {
        preferences.setFirebaseUserId(FirebaseAuth.getInstance().getUid());
        mRepository.createOrUpdatePreferences(FirebaseAuth.getInstance().getUid(), preferences);
    }

}
