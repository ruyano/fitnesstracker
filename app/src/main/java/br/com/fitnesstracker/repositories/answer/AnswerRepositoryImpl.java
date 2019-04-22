package br.com.fitnesstracker.repositories.answer;

import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import br.com.fitnesstracker.models.FisicalAvaliation;

public class AnswerRepositoryImpl implements AnswerRepository {

    private static final String ANSWERS_TABLE = "answers";

    private DatabaseReference mDatabaseReference;

    public AnswerRepositoryImpl() {
        this.mDatabaseReference = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void createAnswer(String userId, FisicalAvaliation fisicalAvaliation) {
        String key = mDatabaseReference.child(ANSWERS_TABLE).push().getKey();
        mDatabaseReference.child(ANSWERS_TABLE)
                .child(userId)
                .child(key == null ? "" : key)
                .setValue(fisicalAvaliation)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.i("","");
                        } else {
                            Log.i("","");
                        }
                    }
                });
    }




}
