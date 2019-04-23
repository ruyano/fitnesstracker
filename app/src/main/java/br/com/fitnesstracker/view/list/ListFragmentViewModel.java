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

    public void init() {
        mFisicalAvaliations = new MutableLiveData<>();
        mAdapter = new FisicalAvaliationListAdapter(this);
        mRepository = new FisicalAvaliationRepositoryImpl();
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
        mRepository.getFisicalAvaliationList(FirebaseAuth.getInstance().getUid());
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

    public void deleteFisicalAvaliation(Integer position) {
        // TODO - dialog de confirmação
        FisicalAvaliation fisicalAvaliation = getFisicalAvaliationAtPosition(position);
        mRepository.deleteFisicalAvaliation(FirebaseAuth.getInstance().getUid(), fisicalAvaliation);
    }

    public void updateFisicalAvaliation(Integer position) {
        // TODO - abrir QAndA novamente e chamar o update quando terminar de editar;
        FisicalAvaliation fisicalAvaliation = getFisicalAvaliationAtPosition(position);
        fisicalAvaliation.setDate("21/21/2121");
        mRepository.updateFisicalAvaliation(FirebaseAuth.getInstance().getUid(), fisicalAvaliation);
    }

}
