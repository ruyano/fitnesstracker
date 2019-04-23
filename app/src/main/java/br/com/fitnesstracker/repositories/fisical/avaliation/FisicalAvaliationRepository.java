package br.com.fitnesstracker.repositories.fisical.avaliation;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import br.com.fitnesstracker.models.FisicalAvaliation;

public interface FisicalAvaliationRepository {

    void createFisicalAvaliation(String userId, FisicalAvaliation fisicalAvaliation);

    void createOrUpdateFisicalAvaliation(String userId, FisicalAvaliation fisicalAvaliation);

    void getFisicalAvaliationList(String userId);

    MutableLiveData<ArrayList<FisicalAvaliation>> getFisicalAvaliationListLiveData();

    void updateFisicalAvaliation(String userId, FisicalAvaliation fisicalAvaliation);

    void deleteFisicalAvaliation(String userId, FisicalAvaliation fisicalAvaliation);

}
