package br.com.fitnesstracker.view.list;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import br.com.fitnesstracker.R;
import br.com.fitnesstracker.databinding.FragmentListBinding;
import br.com.fitnesstracker.models.FisicalAvaliation;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ListFragment extends Fragment {

    private static final String RECYCLERVIEW_SAVED_STATE = "RECYCLERVIEW_SAVED_STATE";
    private FragmentListBinding mFragmentListBinding;
    private ListFragmentViewModel mViewModel;

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

}
