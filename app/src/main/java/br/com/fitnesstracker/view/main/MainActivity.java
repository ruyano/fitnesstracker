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
import com.google.firebase.auth.FirebaseAuth;

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
import br.com.fitnesstracker.models.FisicalAvaliation;
import br.com.fitnesstracker.repositories.answer.AnswerRepository;
import br.com.fitnesstracker.repositories.answer.AnswerRepositoryImpl;

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

        String[] avaliationItens = getResources().getStringArray(R.array.fisical_avaliation_itens);
        for (String iten : avaliationItens) {
            Question.AnswerType answerType = Question.AnswerType.DOUBLE;
            if (iten.equals(avaliationItens[0])) {
                answerType = Question.AnswerType.DATE;
            }
            Question question = new Question(iten, answerType);
            questions.add(question);
        }

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
            FisicalAvaliation fisicalAvaliation = getFisicalAvaliationObj(questions);
            AnswerRepository answerRepository = new AnswerRepositoryImpl();
            answerRepository.createAnswer(FirebaseAuth.getInstance().getUid(), fisicalAvaliation);
        }
    }

    private FisicalAvaliation getFisicalAvaliationObj(ArrayList<Question> questions) {
        FisicalAvaliation fisicalAvaliation = new FisicalAvaliation();
        for (Question q : questions) {
            if (q.getQuestion().equals(getString(R.string.question_date))) {
                fisicalAvaliation.setDate(q.getAnswer());
            } else if (q.getQuestion().equals(getString(R.string.question_neck))) {
                fisicalAvaliation.setNeck(Double.valueOf(q.getAnswer()));
            } else if (q.getQuestion().equals(getString(R.string.question_shoulders))) {
                fisicalAvaliation.setShoulders(Double.valueOf(q.getAnswer()));
            } else if (q.getQuestion().equals(getString(R.string.question_breastplate))) {
                fisicalAvaliation.setBreastplate(Double.valueOf(q.getAnswer()));
            } else if (q.getQuestion().equals(getString(R.string.question_waist))) {
                fisicalAvaliation.setWaist(Double.valueOf(q.getAnswer()));
            } else if (q.getQuestion().equals(getString(R.string.question_abdomen))) {
                fisicalAvaliation.setAbdomen(Double.valueOf(q.getAnswer()));
            } else if (q.getQuestion().equals(getString(R.string.question_rightArm))) {
                fisicalAvaliation.setRightArm(Double.valueOf(q.getAnswer()));
            } else if (q.getQuestion().equals(getString(R.string.question_leftArm))) {
                fisicalAvaliation.setLeftArm(Double.valueOf(q.getAnswer()));
            } else if (q.getQuestion().equals(getString(R.string.question_rightLeg))) {
                fisicalAvaliation.setRightLeg(Double.valueOf(q.getAnswer()));
            } else if (q.getQuestion().equals(getString(R.string.question_leftLeg))) {
                fisicalAvaliation.setLeftLeg(Double.valueOf(q.getAnswer()));
            } else if (q.getQuestion().equals(getString(R.string.question_rightCalf))) {
                fisicalAvaliation.setRightCalf(Double.valueOf(q.getAnswer()));
            } else if (q.getQuestion().equals(getString(R.string.question_leftCalf))) {
                fisicalAvaliation.setLeftCalf(Double.valueOf(q.getAnswer()));
            } else if (q.getQuestion().equals(getString(R.string.question_weight))) {
                fisicalAvaliation.setWeight(Double.valueOf(q.getAnswer()));
            }
        }

        return fisicalAvaliation;
    }

}
