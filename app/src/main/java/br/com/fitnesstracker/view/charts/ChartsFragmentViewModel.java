package br.com.fitnesstracker.view.charts;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import br.com.fitnesstracker.models.FisicalAvaliation;
import br.com.fitnesstracker.repositories.fisical.avaliation.FisicalAvaliationRepository;
import br.com.fitnesstracker.repositories.fisical.avaliation.FisicalAvaliationRepositoryImpl;

public class ChartsFragmentViewModel extends ViewModel {

    private ChartsFragmentModel mModel;
    private FisicalAvaliationRepository mRepository;
    private ArrayList<String> spinnerEntries;

    public void init() {
        mModel = new ChartsFragmentModel();
        mRepository = new FisicalAvaliationRepositoryImpl();
    }

    public void setSpinnerEntries(ArrayList<String> entries) {
        spinnerEntries = entries;
        mModel.chartSpinnerEntries.set(entries);
    }

    public ChartsFragmentModel getModel() {
        return mModel;
    }

    public MutableLiveData<ArrayList<FisicalAvaliation>> getFisicalAvaliationListLiveData() {
        return mRepository.getFisicalAvaliationListLiveData();
    }

    public void getList() {
        showLoading();
        mRepository.readFisicalAvaliationForUser(FirebaseAuth.getInstance().getUid());
    }

    public void showLoading() {
        mModel.chartProgressVisible.set(true);
        mModel.chartVisible.set(false);
    }

    public void hideLoading() {
        mModel.chartProgressVisible.set(false);
        mModel.chartVisible.set(true);
    }

    public void teste(String value) {
        Log.i("", "");
    }

}
