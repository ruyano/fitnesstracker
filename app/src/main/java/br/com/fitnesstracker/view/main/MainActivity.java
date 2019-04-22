package br.com.fitnesstracker.view.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.qanda.utils.QAndA;
import com.example.qanda.models.Question;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import br.com.fitnesstracker.ChartsFragment;
import br.com.fitnesstracker.ListFragment;
import br.com.fitnesstracker.R;
import br.com.fitnesstracker.SettingsFragment;

public class MainActivity extends AppCompatActivity {

    public static Intent getIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    private ChartsFragment chartsFragment = new ChartsFragment();
    private ListFragment listFragment = new ListFragment();
    private SettingsFragment settingsFragment = new SettingsFragment();
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private Fragment currentFragment = chartsFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_charts:
                    fragmentManager.beginTransaction().hide(currentFragment).show(chartsFragment).commit();
                    currentFragment = chartsFragment;
                    return true;
                case R.id.navigation_list:
                    fragmentManager.beginTransaction().hide(currentFragment).show(listFragment).commit();
                    currentFragment = listFragment;
                    return true;
                case R.id.navigation_settings:
                    fragmentManager.beginTransaction().hide(currentFragment).show(settingsFragment).commit();
                    currentFragment = settingsFragment;
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fragmentManager.beginTransaction().add(R.id.main_container, chartsFragment).commit();
        fragmentManager.beginTransaction().add(R.id.main_container, listFragment).hide(listFragment).commit();
        fragmentManager.beginTransaction().add(R.id.main_container, settingsFragment).hide(settingsFragment).commit();
    }

    public void startQAndA(View view) {
        QAndA.startQAndA(this, questsGenerator());
    }

    private ArrayList<Question> questsGenerator() {
        ArrayList<Question> questions = new ArrayList<>();

        Question question = new Question(getString(R.string.question_date), Question.AnswerType.DATE);
        questions.add(question);

        Question question1 = new Question(getString(R.string.question_abdomen), Question.AnswerType.DOUBLE);
        questions.add(question1);

        Question question2 = new Question(getString(R.string.question_waist), Question.AnswerType.DOUBLE);
        questions.add(question2);

        Question question3 = new Question(getString(R.string.question_breastplate), Question.AnswerType.DOUBLE);
        questions.add(question3);

        return questions;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == QAndA.QANDA_REQUEST_CODE &&
                resultCode == RESULT_OK &&
                data != null &&
                data.hasExtra(QAndA.QUESTIONS_LIST)) {
            ArrayList<Question> questions = data.getParcelableArrayListExtra(QAndA.QUESTIONS_LIST);
            Log.i("","");
        }
    }
}
