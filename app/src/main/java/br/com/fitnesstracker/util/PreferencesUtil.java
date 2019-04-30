package br.com.fitnesstracker.util;

import android.content.SharedPreferences;
import android.content.res.Resources;

import br.com.fitnesstracker.R;
import br.com.fitnesstracker.models.Preferences;

public class PreferencesUtil {

    private SharedPreferences mSharedPreferences;
    private Resources mResources;

    public PreferencesUtil(SharedPreferences sharedPreferences, Resources resources) {
        this.mSharedPreferences = sharedPreferences;
        this.mResources = resources;
    }

    public void updateLocalPreferences(Preferences preferences) {
        SharedPreferences.Editor sPEditor = mSharedPreferences.edit();
        sPEditor.putBoolean(mResources.getString(R.string.preference_neck), preferences.getNeck()).apply();
        sPEditor.putBoolean(mResources.getString(R.string.preference_shoulders), preferences.getShoulders()).apply();
        sPEditor.putBoolean(mResources.getString(R.string.preference_breastplate), preferences.getBreastplate()).apply();
        sPEditor.putBoolean(mResources.getString(R.string.preference_waist), preferences.getWaist()).apply();
        sPEditor.putBoolean(mResources.getString(R.string.preference_abdomen), preferences.getAbdomen()).apply();
        sPEditor.putBoolean(mResources.getString(R.string.preference_rightArm), preferences.getRightArm()).apply();
        sPEditor.putBoolean(mResources.getString(R.string.preference_leftArm), preferences.getLeftArm()).apply();
        sPEditor.putBoolean(mResources.getString(R.string.preference_rightLeg), preferences.getRightLeg()).apply();
        sPEditor.putBoolean(mResources.getString(R.string.preference_leftLeg), preferences.getLeftLeg()).apply();
        sPEditor.putBoolean(mResources.getString(R.string.preference_rightCalf), preferences.getRightCalf()).apply();
        sPEditor.putBoolean(mResources.getString(R.string.preference_leftCalf), preferences.getLeftCalf()).apply();
        sPEditor.putBoolean(mResources.getString(R.string.preference_weight), preferences.getWeight()).apply();
    }

    public Preferences getPreferencesObj() {
        Preferences preferences = new Preferences();
        preferences.setNeck(getKeyValue(R.string.preference_neck));
        preferences.setShoulders(getKeyValue(R.string.preference_shoulders));
        preferences.setBreastplate(getKeyValue(R.string.preference_breastplate));
        preferences.setWaist(getKeyValue(R.string.preference_waist));
        preferences.setAbdomen(getKeyValue(R.string.preference_abdomen));
        preferences.setRightArm(getKeyValue(R.string.preference_rightArm));
        preferences.setLeftArm(getKeyValue(R.string.preference_leftArm));
        preferences.setRightLeg(getKeyValue(R.string.preference_rightLeg));
        preferences.setLeftLeg(getKeyValue(R.string.preference_leftLeg));
        preferences.setRightCalf(getKeyValue(R.string.preference_rightCalf));
        preferences.setLeftCalf(getKeyValue(R.string.preference_leftCalf));
        preferences.setWeight(getKeyValue(R.string.preference_weight));
        return preferences;
    }

    private Boolean getKeyValue(Integer keyResource) {
        return mSharedPreferences.getBoolean(mResources.getString(keyResource), true);
    }
}
