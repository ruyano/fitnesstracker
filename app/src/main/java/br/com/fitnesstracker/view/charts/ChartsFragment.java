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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mFragmentChartsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_charts, container, false);
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
        mViewModel.getFisicalAvaliationListLiveData().observe(this, new Observer<ArrayList<FisicalAvaliation>>() {
            @Override
            public void onChanged(ArrayList<FisicalAvaliation> fisicalAvaliations) {
                mViewModel.hideLoading();
                updateChart(fisicalAvaliations);
            }
        });
    }

    private void updateChart(ArrayList<FisicalAvaliation> fisicalAvaliations) {
        ArrayList<Entry> entries = new ArrayList<>();
        for (int i = 0; i < fisicalAvaliations.size(); i++) {
            // TODO - validar null
            Float peso = Float.valueOf(String.valueOf(fisicalAvaliations.get(i).getValueByQuestion(getContext(), getString(R.string.question_leftCalf))));
            entries.add(new Entry(i, peso));
        }
        LineDataSet dataSet = new LineDataSet(entries, null);
        dataSet.setColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        dataSet.setValueTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
        LineData data = new LineData(dataSet);
        mFragmentChartsBinding.chart.setData(data);
        mFragmentChartsBinding.chart.invalidate();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        mViewModel.setSpinnerEntries(mQuestionsUtil.getQuestionsStringArrayForSpinner());
    }
}
