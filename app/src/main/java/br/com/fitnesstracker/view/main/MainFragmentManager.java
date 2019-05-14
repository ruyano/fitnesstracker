package br.com.fitnesstracker.view.main;

import br.com.fitnesstracker.view.charts.ChartsFragment;
import br.com.fitnesstracker.view.list.ListFragment;
import br.com.fitnesstracker.view.settings.SettingsFragment;

public class MainFragmentManager {

    private ChartsFragment mChartsFragment;
    private ListFragment mListFragment;
    private SettingsFragment mSettingsFragment;
    private SelectedFragmentType mCurrentFragmentSelected;

    private static MainFragmentManager mInstance;

    public static MainFragmentManager getInstance() {
        if (mInstance == null)
            mInstance = new MainFragmentManager();
        return mInstance;
    }

    public ChartsFragment getChartsFragment() {
        if (mChartsFragment == null)
            mChartsFragment = new ChartsFragment();
        return mChartsFragment;
    }

    public ListFragment getListFragment() {
        if (mListFragment == null)
            mListFragment = new ListFragment();
        return mListFragment;
    }

    public SettingsFragment getSettingsFragment() {
        if (mSettingsFragment == null)
            mSettingsFragment = new SettingsFragment();
        return mSettingsFragment;
    }

    public SelectedFragmentType getCurrentFragmentSelected() {
        if (mCurrentFragmentSelected == null)
            mCurrentFragmentSelected = SelectedFragmentType.CHARTS;
        return mCurrentFragmentSelected;
    }

    public void setCurrentFragmentSelected(SelectedFragmentType currentFragmentSelected) {
        this.mCurrentFragmentSelected = currentFragmentSelected;
    }

    public enum SelectedFragmentType {
        CHARTS,
        LIST,
        SETTINGS
    }
}
