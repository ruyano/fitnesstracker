package br.com.fitnesstracker.view.list;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.qanda.models.Question;
import com.example.qanda.utils.QAndA;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import br.com.fitnesstracker.R;
import br.com.fitnesstracker.databinding.FragmentListBinding;
import br.com.fitnesstracker.models.FisicalAvaliation;
import br.com.fitnesstracker.repositories.fisical.avaliation.FisicalAvaliationRepository;
import br.com.fitnesstracker.repositories.fisical.avaliation.FisicalAvaliationRepositoryImpl;
import br.com.fitnesstracker.util.AppUtil;
import br.com.fitnesstracker.util.QuestionsUtil;

import static android.app.Activity.RESULT_OK;

public class ListFragment extends Fragment {

    private static final String RECYCLERVIEW_SAVED_STATE = "RECYCLERVIEW_SAVED_STATE";
    private FragmentListBinding mFragmentListBinding;
    private ListFragmentViewModel mViewModel;
    private FisicalAvaliation mEditingFisicalAvaliation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mFragmentListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false);
        setupViewModel(savedInstanceState);
        return mFragmentListBinding.getRoot();
    }

    private void setupViewModel(Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(ListFragmentViewModel.class);
        if (savedInstanceState == null) {
            mViewModel.init();
        }
        mFragmentListBinding.setViewModel(mViewModel);

        setupRecyclerView();
        setupListObserver();
        mViewModel.getList();
        setupEditButton();
        setupDeleteButton();
    }

    private void setupDeleteButton() {
        mViewModel.getDeleteButtonLiveData().observe(this, new Observer<FisicalAvaliation>() {
            @Override
            public void onChanged(final FisicalAvaliation fisicalAvaliation) {
                AppUtil.showAlertDialog(getContext(),
                        getResources().getString(R.string.atention),
                        getString(R.string.deleteDialogMessage) + fisicalAvaliation.getDate(),
                        getString(R.string.yes),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mViewModel.deleteFisicalAvaliation(fisicalAvaliation);
                            }
                        },
                        getString(R.string.no),
                        null);
            }
        });
    }

    private void setupEditButton() {
        mViewModel.getEditButtonLiveData().observe(this, new Observer<FisicalAvaliation>() {
            @Override
            public void onChanged(FisicalAvaliation fisicalAvaliation) {
                mEditingFisicalAvaliation = fisicalAvaliation;
                QuestionsUtil questionsUtil = new QuestionsUtil(PreferenceManager
                        .getDefaultSharedPreferences(getContext()), getResources());
                QAndA.startQAndA(ListFragment.this, questionsUtil.getQuestionsArray(fisicalAvaliation));
            }
        });
    }

    private void setupListObserver() {
        mViewModel.getFisicalAvaliationListLiveData().observe(this, new Observer<ArrayList<FisicalAvaliation>>() {
            @Override
            public void onChanged(ArrayList<FisicalAvaliation> fisicalAvaliations) {
                mViewModel.setFisicalAvaliations(fisicalAvaliations);
            }
        });
    }

    private void setupRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mFragmentListBinding.recyclerView.setLayoutManager(linearLayoutManager);
        mFragmentListBinding.recyclerView.setAdapter(mViewModel.getAdapter());
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Parcelable recycerViewSavedState = mFragmentListBinding.recyclerView.getLayoutManager().onSaveInstanceState();
        if (recycerViewSavedState != null)
            outState.putParcelable(RECYCLERVIEW_SAVED_STATE, recycerViewSavedState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState == null)
            return;
        Parcelable recycerViewSavedState = savedInstanceState.getParcelable(RECYCLERVIEW_SAVED_STATE);
        if (recycerViewSavedState != null)
            mFragmentListBinding.recyclerView.getLayoutManager().onRestoreInstanceState(recycerViewSavedState);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == QAndA.QANDA_REQUEST_CODE &&
                resultCode == RESULT_OK &&
                data != null &&
                data.hasExtra(QAndA.QUESTIONS_LIST)) {
            ArrayList<Question> questions = data.getParcelableArrayListExtra(QAndA.QUESTIONS_LIST);
            FisicalAvaliation fisicalAvaliation = AppUtil.getFisicalAvaliationObj(getContext(), questions);
            if (mEditingFisicalAvaliation != null)
                fisicalAvaliation.setFirebaseKey(mEditingFisicalAvaliation.getFirebaseKey());
            FisicalAvaliationRepository fisicalAvaliationRepository = new FisicalAvaliationRepositoryImpl();
            fisicalAvaliationRepository.createOrUpdateFisicalAvaliation(FirebaseAuth.getInstance().getUid(), fisicalAvaliation);
        }
    }
}
