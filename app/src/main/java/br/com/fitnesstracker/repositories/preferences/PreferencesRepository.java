package br.com.fitnesstracker.repositories.preferences;

import androidx.lifecycle.MutableLiveData;

import br.com.fitnesstracker.models.Preferences;

public interface PreferencesRepository {

    // C - Create
    void createPreferences(String userId, Preferences preference);

    // R - Read
    void readPreferencesForUser(String userId);
    MutableLiveData<Preferences> getPreferencesLiveData();

    // U - Update
    void updatePreferences(String userId, Preferences preference);

    // D - Delete
    void deletePreferences(String userId, Preferences preference);

}
