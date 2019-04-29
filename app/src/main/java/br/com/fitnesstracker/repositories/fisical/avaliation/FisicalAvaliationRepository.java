package br.com.fitnesstracker.repositories.fisical.avaliation;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import br.com.fitnesstracker.models.FisicalAvaliation;

public interface FisicalAvaliationRepository {

    // C - Create
    void createFisicalAvaliation(String userId, FisicalAvaliation fisicalAvaliation);

    void createOrUpdateFisicalAvaliation(String userId, FisicalAvaliation fisicalAvaliation);

    // R - Read
    void readFisicalAvaliationForUser(String userId);
    MutableLiveData<ArrayList<FisicalAvaliation>> getFisicalAvaliationListLiveData();

    // U - Update
    void updateFisicalAvaliation(String userId, FisicalAvaliation fisicalAvaliation);

    // D - Delete
    void deleteFisicalAvaliation(String userId, FisicalAvaliation fisicalAvaliation);

}
