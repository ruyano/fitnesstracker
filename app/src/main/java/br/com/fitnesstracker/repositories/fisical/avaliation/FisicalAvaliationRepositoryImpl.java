package br.com.fitnesstracker.repositories.fisical.avaliation;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import br.com.fitnesstracker.models.FisicalAvaliation;

public class FisicalAvaliationRepositoryImpl implements FisicalAvaliationRepository {

    private static final String FISICAL_AVALIATION_TABLE = "fisical_avaliations";

    private DatabaseReference mDatabaseReference;

    private MutableLiveData<ArrayList<FisicalAvaliation>> mFisicalAvaliations;

    public FisicalAvaliationRepositoryImpl() {
        this.mDatabaseReference = FirebaseDatabase.getInstance().getReference();
    }

    // C - Create
    @Override
    public void createFisicalAvaliation(String userId, FisicalAvaliation fisicalAvaliation) {
        String key = mDatabaseReference.child(FISICAL_AVALIATION_TABLE).push().getKey();
        fisicalAvaliation.setFirebaseKey(key);
        mDatabaseReference.child(FISICAL_AVALIATION_TABLE)
                .child(userId)
                .child(key == null ? "" : key)
                .setValue(fisicalAvaliation);
    }

    @Override
    public void createOrUpdateFisicalAvaliation(String userId, FisicalAvaliation fisicalAvaliation) {
        if (fisicalAvaliation.getFirebaseKey() == null) {
            createFisicalAvaliation(userId, fisicalAvaliation);
        } else {
            updateFisicalAvaliation(userId, fisicalAvaliation);
        }
    }

    // R - Read
    @Override
    public void readFisicalAvaliationForUser(String userId) {
        mDatabaseReference.child(FISICAL_AVALIATION_TABLE)
                .child(userId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (mFisicalAvaliations == null)
                            return;
                        ArrayList<FisicalAvaliation> fisicalAvaliations = new ArrayList<>();
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            FisicalAvaliation fisicalAvaliation = ds.getValue(FisicalAvaliation.class);
                            fisicalAvaliations.add(fisicalAvaliation);
                        }
                        mFisicalAvaliations.postValue(fisicalAvaliations);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {}
                });
    }

    @Override
    public MutableLiveData<ArrayList<FisicalAvaliation>> getFisicalAvaliationListLiveData() {
        if (mFisicalAvaliations == null)
            mFisicalAvaliations = new MutableLiveData<>();
        return mFisicalAvaliations;
    }

    // U - Update
    @Override
    public void updateFisicalAvaliation(String userId, FisicalAvaliation fisicalAvaliation) {
        Map<String, Object> fisicalAvaliationUpdate = new HashMap<>();
        fisicalAvaliationUpdate.put(fisicalAvaliation.getFirebaseKey(), fisicalAvaliation);

        mDatabaseReference.child(FISICAL_AVALIATION_TABLE)
                .child(userId)
                .updateChildren(fisicalAvaliationUpdate);
    }

    // D - Delete
    @Override
    public void deleteFisicalAvaliation(String userId, FisicalAvaliation fisicalAvaliation) {
        mDatabaseReference.child(FISICAL_AVALIATION_TABLE)
                .child(userId)
                .child(fisicalAvaliation.getFirebaseKey())
                .removeValue();
    }
}
