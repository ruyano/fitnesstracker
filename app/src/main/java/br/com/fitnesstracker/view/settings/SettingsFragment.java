package br.com.fitnesstracker.view.settings;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.lifecycle.ViewModelProviders;
import androidx.preference.CheckBoxPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import com.google.firebase.auth.FirebaseAuth;

import br.com.fitnesstracker.R;
import br.com.fitnesstracker.models.Preferences;
import br.com.fitnesstracker.util.PreferencesUtil;
import br.com.fitnesstracker.view.login.LoginActivity;

public class SettingsFragment extends PreferenceFragmentCompat
        implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }

    private SettingsFragmentViewModel mViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupViewModel(savedInstanceState);
        setupLogoutButton();
    }

    @Override
    public void onResume() {
        super.onResume();
        PreferenceManager.getDefaultSharedPreferences(getContext())
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onDestroy() {
        PreferenceManager.getDefaultSharedPreferences(getContext())
                .unregisterOnSharedPreferenceChangeListener(this);
        super.onDestroy();
    }

    private void setupViewModel(Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(SettingsFragmentViewModel.class);
        if (savedInstanceState == null)
            mViewModel.init();
    }

    private void setupLogoutButton() {
        Preference logoutPreference = findPreference(getString(R.string.logout));
        logoutPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getContext(), LoginActivity.class));
                getActivity().finish();
                return true;
            }
        });
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preferences preferences = new PreferencesUtil(PreferenceManager
                .getDefaultSharedPreferences(getContext()), getResources()).getPreferencesObj();
        mViewModel.updatePreferences(preferences);

        CheckBoxPreference checkBoxPreference = findPreference(key);
        checkBoxPreference.setChecked(sharedPreferences.getBoolean(key, true));
    }
}
