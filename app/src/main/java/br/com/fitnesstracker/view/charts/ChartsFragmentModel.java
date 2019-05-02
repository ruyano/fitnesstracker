package br.com.fitnesstracker.view.charts;

import androidx.databinding.ObservableField;

import java.util.ArrayList;

public class ChartsFragmentModel {

    public ObservableField<ArrayList<String>> chartSpinnerEntries = new ObservableField<>();
    public ObservableField<String> chartSpinnerSelectedValue = new ObservableField<>();

    public ObservableField<Boolean> chartProgressVisible = new ObservableField<>(true);
    public ObservableField<Boolean> chartVisible = new ObservableField<>(false);

}
