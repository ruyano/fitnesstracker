package br.com.fitnesstracker.view.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.qanda.models.Question;
import com.example.qanda.utils.QAndA;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import br.com.fitnesstracker.R;
import br.com.fitnesstracker.databinding.ActivityMainBinding;
import br.com.fitnesstracker.models.FisicalAvaliation;
import br.com.fitnesstracker.models.Preferences;
import br.com.fitnesstracker.repositories.fisical.avaliation.FisicalAvaliationRepository;
import br.com.fitnesstracker.repositories.fisical.avaliation.FisicalAvaliationRepositoryImpl;
import br.com.fitnesstracker.util.AppUtil;
import br.com.fitnesstracker.util.PreferencesUtil;
import br.com.fitnesstracker.util.QuestionsUtil;
import br.com.fitnesstracker.view.charts.ChartsFragment;
import br.com.fitnesstracker.view.list.ListFragment;
import br.com.fitnesstracker.view.settings.SettingsFragment;

public class MainActivity extends AppCompatActivity {

    public static Intent getIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    private MainActivityViewModel mViewModel;

    private ChartsFragment chartsFragment = new ChartsFragment();
    private ListFragment listFragment = new ListFragment();
    private SettingsFragment settingsFragment = new SettingsFragment();
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private Fragment currentFragment = chartsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupBindings(savedInstanceState);
        setupBottonNavigation();
        setupFragments();
    }

    private void setupBindings(Bundle savedInstanceState) {
        ActivityMainBinding activityMainBinding = DataBindingUtil
                .setContentView(this, R.layout.activity_main);
        mViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        if (savedInstanceState == null)
            mViewModel.init();
        activityMainBinding.setViewModel(mViewModel);

        mViewModel.getPreferences();
        mViewModel.getPreferencesLiveData().observe(this, new Observer<Preferences>() {
            @Override
            public void onChanged(Preferences preferences) {
                new PreferencesUtil(PreferenceManager
                        .getDefaultSharedPreferences(MainActivity.this), getResources())
                        .updateLocalPreferences(preferences);
            }
        });
    }

    private void setupBottonNavigation() {
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_charts:
                        fragmentManager.beginTransaction()
                                .hide(currentFragment)
                                .show(chartsFragment)
                                .commit();
                        currentFragment = chartsFragment;
                        mViewModel.setFabButtonVisible(true);
                        return true;
                    case R.id.navigation_list:
                        fragmentManager.beginTransaction()
                                .hide(currentFragment)
                                .show(listFragment)
                                .commit();
                        currentFragment = listFragment;
                        mViewModel.setFabButtonVisible(true);
                        return true;
                    case R.id.navigation_settings:
                        fragmentManager.beginTransaction()
                                .hide(currentFragment)
                                .show(settingsFragment)
                                .commit();
                        currentFragment = settingsFragment;
                        mViewModel.setFabButtonVisible(false);
                        return true;
                }
                return false;
            }
        });
    }

    private void setupFragments() {
        fragmentManager.beginTransaction()
                .add(R.id.main_container, chartsFragment)
                .commit();
        fragmentManager.beginTransaction()
                .add(R.id.main_container, listFragment).hide(listFragment)
                .commit();
        fragmentManager.beginTransaction()
                .add(R.id.main_container, settingsFragment).hide(settingsFragment)
                .commit();
    }

    public void startQAndA(View view) {
        QuestionsUtil questionsUtil = new QuestionsUtil(PreferenceManager
                .getDefaultSharedPreferences(this), getResources());
        QAndA.startQAndA(this, questionsUtil.getQuestionsArray());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == QAndA.QANDA_REQUEST_CODE &&
                resultCode == RESULT_OK &&
                data != null &&
                data.hasExtra(QAndA.QUESTIONS_LIST)) {
            ArrayList<Question> questions = data.getParcelableArrayListExtra(QAndA.QUESTIONS_LIST);
            FisicalAvaliation fisicalAvaliation = AppUtil.getFisicalAvaliationObj(this, questions);
            FisicalAvaliationRepository fisicalAvaliationRepository = new FisicalAvaliationRepositoryImpl();
            fisicalAvaliationRepository.createOrUpdateFisicalAvaliation(FirebaseAuth.getInstance().getUid(), fisicalAvaliation);
        }
    }

}
