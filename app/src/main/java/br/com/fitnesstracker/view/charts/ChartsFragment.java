package br.com.fitnesstracker.view.charts;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

import br.com.fitnesstracker.R;
import br.com.fitnesstracker.databinding.FragmentChartsBinding;
import br.com.fitnesstracker.models.FisicalAvaliation;
import br.com.fitnesstracker.util.QuestionsUtil;

public class ChartsFragment extends Fragment
        implements SharedPreferences.OnSharedPreferenceChangeListener {

    private FragmentChartsBinding mFragmentChartsBinding;
    private ChartsFragmentViewModel mViewModel;
    private QuestionsUtil mQuestionsUtil;
    private ArrayList<FisicalAvaliation> mFsicalAvaliations;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mFragmentChartsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_charts, container, false);
        setupChartConfigs();
        mQuestionsUtil = new QuestionsUtil(PreferenceManager.getDefaultSharedPreferences(getContext()), getResources());
        setupViewModel(savedInstanceState);
        return mFragmentChartsBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        androidx.preference.PreferenceManager.getDefaultSharedPreferences(getContext())
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onDestroy() {
        androidx.preference.PreferenceManager.getDefaultSharedPreferences(getContext())
                .unregisterOnSharedPreferenceChangeListener(this);
        super.onDestroy();
    }

    private void setupViewModel(Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(ChartsFragmentViewModel.class);
        if (savedInstanceState == null)
            mViewModel.init();
        mFragmentChartsBinding.setViewModel(mViewModel);

        mViewModel.setSpinnerEntries(mQuestionsUtil.getQuestionsStringArrayForSpinner());

        setupListObserver();
        mViewModel.getList();
    }

    private void setupListObserver() {
        mViewModel.getModel().chartSpinnerSelectedPosition.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer position) {
                if (mFsicalAvaliations != null && !mFsicalAvaliations.isEmpty())
                    updateChart();
            }
        });

        mViewModel.getFisicalAvaliationListLiveData().observe(this, new Observer<ArrayList<FisicalAvaliation>>() {
            @Override
            public void onChanged(ArrayList<FisicalAvaliation> fisicalAvaliations) {
                mViewModel.hideLoading();
                mFsicalAvaliations = fisicalAvaliations;
                updateChart();
            }
        });
    }

    private void updateChart() {
        String key = mQuestionsUtil.getQuestionsStringArrayForSpinner().get(0);
        if (mFragmentChartsBinding.spinner.getSelectedItem() != null)
            key = mFragmentChartsBinding.spinner.getSelectedItem().toString();
        ArrayList<Entry> entries = new ArrayList<>();
        for (int i = 0; i < mFsicalAvaliations.size(); i++) {
            Double entrieValue = mFsicalAvaliations.get(i).getValueByQuestion(getContext(), key);
            if (entrieValue != null)
                entries.add(new Entry(i, Float.valueOf(String.valueOf(entrieValue))));
        }
        LineDataSet dataSet = new LineDataSet(entries, key);
        dataSet.setColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        dataSet.setValueTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
        dataSet.setDrawCircles(false);
        LineData data = new LineData(dataSet);
        mFragmentChartsBinding.chart.setData(data);
        mFragmentChartsBinding.chart.invalidate();
    }

    private void setupChartConfigs() {
        mFragmentChartsBinding.chart.getAxisLeft().setEnabled(false);
        mFragmentChartsBinding.chart.getAxisRight().setEnabled(false);
        mFragmentChartsBinding.chart.getXAxis().setEnabled(false);
        mFragmentChartsBinding.chart.getLegend().setEnabled(false);
        mFragmentChartsBinding.chart.setDrawBorders(false);
        mFragmentChartsBinding.chart.setDrawGridBackground(false);
        mFragmentChartsBinding.chart.setDrawMarkers(false);
        mFragmentChartsBinding.chart.getDescription().setEnabled(false);
        mFragmentChartsBinding.chart.setClickable(false);
        mFragmentChartsBinding.chart.setLongClickable(false);
        mFragmentChartsBinding.chart.setTouchEnabled(false);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        mViewModel.setSpinnerEntries(mQuestionsUtil.getQuestionsStringArrayForSpinner());
    }
}
