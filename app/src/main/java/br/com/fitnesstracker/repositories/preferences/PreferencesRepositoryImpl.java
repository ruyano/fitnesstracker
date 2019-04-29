package br.com.fitnesstracker.repositories.preferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import br.com.fitnesstracker.models.Preferences;

public class PreferencesRepositoryImpl implements PreferencesRepository {

    private static final String PREFERENCES_TABLE = "preferences";

    private DatabaseReference mDatabaseReference;

    private MutableLiveData<Preferences> mPreferences;

    public PreferencesRepositoryImpl() {
        this.mDatabaseReference = FirebaseDatabase.getInstance().getReference();
    }

    // C - Create
    @Override
    public void createPreferences(String userId, Preferences preference) {
        String key = mDatabaseReference.child(PREFERENCES_TABLE).push().getKey();
        preference.setFirebaseKey(key);
        mDatabaseReference.child(PREFERENCES_TABLE)
                .child(userId)
                .child(key == null ? "" : key)
                .setValue(preference);
    }

    // R - Read
    @Override
    public void readPreferencesForUser(String userId) {
        mDatabaseReference.child(PREFERENCES_TABLE)
                .child(userId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (mPreferences == null)
                            return;
                        DataSnapshot ds = dataSnapshot.getChildren().iterator().next();
                        Preferences preferences = ds.getValue(Preferences.class);
                        mPreferences.postValue(preferences);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) { }
                });
    }

    @Override
    public MutableLiveData<Preferences> getPreferencesLiveData() {
        if (mPreferences == null)
            mPreferences = new MutableLiveData<>();
        return mPreferences;
    }


    // U - Update
    @Override
    public void updatePreferences(String userId, Preferences preference) {
        Map<String, Object> preferencesUpdate = new HashMap<>();
        preferencesUpdate.put(preference.getFirebaseKey(), preference);

        mDatabaseReference.child(PREFERENCES_TABLE)
                .child(userId)
                .updateChildren(preferencesUpdate);
    }

    // D - Delete
    @Override
    public void deletePreferences(String userId, Preferences preference) {
        mDatabaseReference.child(PREFERENCES_TABLE)
                .child(userId)
                .child(preference.getFirebaseKey())
                .removeValue();
    }
}
