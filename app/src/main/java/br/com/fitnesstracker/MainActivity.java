package br.com.fitnesstracker;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

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

}
