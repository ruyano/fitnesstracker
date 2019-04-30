package br.com.fitnesstracker.repositories.preferences;

import androidx.lifecycle.MutableLiveData;

import br.com.fitnesstracker.models.Preferences;

public interface PreferencesRepository {

    // C - Create
    void createPreferences(String userId, Preferences preferences);

    void createOrUpdatePreferences(String userId, Preferences preferences);

    // R - Read
    void readPreferencesForUser(String userId);
    MutableLiveData<Preferences> getPreferencesLiveData();

    // U - Update
    void updatePreferences(String userId, Preferences preferences);

    // D - Delete
    void deletePreferences(String userId, Preferences preferences);

}
