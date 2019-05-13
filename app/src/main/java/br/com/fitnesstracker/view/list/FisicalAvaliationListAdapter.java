package br.com.fitnesstracker.view.list;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.ruyano.qanda.BR;

import br.com.fitnesstracker.R;

public class FisicalAvaliationListAdapter extends RecyclerView.Adapter<FisicalAvaliationListAdapter.ViewHolder> {

    private ListFragmentViewModel mViewModel;

    public FisicalAvaliationListAdapter(ListFragmentViewModel viewModel) {
        mViewModel = viewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, viewType, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(mViewModel, position);
    }

    @Override
    public int getItemCount() {
        if (mViewModel.getFisicalAvaliations() == null ||
                mViewModel.getFisicalAvaliations().getValue() == null) {
            return 0;
        }
        return mViewModel.getFisicalAvaliations().getValue().size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.fisical_avaliation_list_item;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ViewDataBinding binding;

        ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(ListFragmentViewModel viewModel, Integer position) {
            binding.setVariable(BR.viewModel, viewModel);
            binding.setVariable(BR.position, position);
            binding.executePendingBindings();
        }

    }

}
