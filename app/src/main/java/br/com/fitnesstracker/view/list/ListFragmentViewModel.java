package br.com.fitnesstracker.view.list;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import br.com.fitnesstracker.models.FisicalAvaliation;
import br.com.fitnesstracker.repositories.fisical.avaliation.FisicalAvaliationRepository;
import br.com.fitnesstracker.repositories.fisical.avaliation.FisicalAvaliationRepositoryImpl;

public class ListFragmentViewModel extends ViewModel {

    private MutableLiveData<ArrayList<FisicalAvaliation>> mFisicalAvaliations;
    private FisicalAvaliationListAdapter mAdapter;
    private FisicalAvaliationRepository mRepository;
    private MutableLiveData<FisicalAvaliation> mEditButtonLiveData;
    private MutableLiveData<FisicalAvaliation> mDeleteButtonLiveData;

    public void init() {
        mFisicalAvaliations = new MutableLiveData<>();
        mAdapter = new FisicalAvaliationListAdapter(this);
        mRepository = new FisicalAvaliationRepositoryImpl();
        mEditButtonLiveData = new MutableLiveData<>();
        mDeleteButtonLiveData = new MutableLiveData<>();
    }

    public RecyclerView.Adapter getAdapter() {
        return mAdapter;
    }

    public MutableLiveData<ArrayList<FisicalAvaliation>> getFisicalAvaliations() {
        return mFisicalAvaliations;
    }

    public void setFisicalAvaliations(ArrayList<FisicalAvaliation> fisicalAvaliations) {
        mFisicalAvaliations.postValue(fisicalAvaliations);
        mAdapter.notifyDataSetChanged();
    }

    public void getList() {
        mRepository.readFisicalAvaliationForUser(FirebaseAuth.getInstance().getUid());
    }

    public MutableLiveData<FisicalAvaliation> getDeleteButtonLiveData() {
        return mDeleteButtonLiveData;
    }

    public MutableLiveData<FisicalAvaliation> getEditButtonLiveData() {
        return mEditButtonLiveData;
    }

    public MutableLiveData<ArrayList<FisicalAvaliation>> getFisicalAvaliationListLiveData() {
        return mRepository.getFisicalAvaliationListLiveData();
    }

    public FisicalAvaliation getFisicalAvaliationAtPosition(Integer position) {
        if (mFisicalAvaliations.getValue() != null
                && mFisicalAvaliations.getValue() != null
                && mFisicalAvaliations.getValue().size() >= position) {
            return mFisicalAvaliations.getValue().get(position);
        }
        return null;

    }

    public void deleteFisicalAvaliation(FisicalAvaliation fisicalAvaliation) {
        mFisicalAvaliations.getValue().remove(fisicalAvaliation);
        mAdapter.notifyDataSetChanged();
        mRepository.deleteFisicalAvaliation(FirebaseAuth.getInstance().getUid(), fisicalAvaliation);
    }

    public void requestToDeleteFisicalAvaliation(Integer position) {
        FisicalAvaliation fisicalAvaliation = getFisicalAvaliationAtPosition(position);
        mDeleteButtonLiveData.postValue(fisicalAvaliation);
    }

    public void updateFisicalAvaliation(Integer position) {
        FisicalAvaliation fisicalAvaliation = getFisicalAvaliationAtPosition(position);
        mEditButtonLiveData.postValue(fisicalAvaliation);
    }

}
