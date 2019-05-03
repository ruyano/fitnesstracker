package br.com.fitnesstracker.view.charts;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

public class ChartsFragmentModel {

    public ObservableField<ArrayList<String>> chartSpinnerEntries = new ObservableField<>();
    public MutableLiveData<Integer> chartSpinnerSelectedPosition = new MutableLiveData<>();

    public ObservableField<Boolean> chartProgressVisible = new ObservableField<>(true);
    public ObservableField<Boolean> chartVisible = new ObservableField<>(false);

}
