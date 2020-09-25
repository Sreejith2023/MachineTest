package app.com.whiterabbittest.screens.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import app.com.whiterabbittest.BR;
import app.com.whiterabbittest.R;
import app.com.whiterabbittest.data.local.EmployeeTable;
import app.com.whiterabbittest.databinding.ItemEmployeeRowBinding;
import app.com.whiterabbittest.model.CustomClickListener;
import app.com.whiterabbittest.viewModel.EmployeeViewModel;

public class HomeEmployeeListAdapter extends RecyclerView.Adapter<HomeEmployeeListAdapter.EmployeeViewHolder> implements CustomClickListener {

    private List<EmployeeTable> employeeTableList;
    private Context context;
    CustomClickListener listener;

    public HomeEmployeeListAdapter(List<EmployeeTable> employeeTableList, Context context, CustomClickListener listener) {
        this.employeeTableList = employeeTableList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemEmployeeRowBinding itemEmployeeRowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_employee_row, parent, false);
        return new EmployeeViewHolder(itemEmployeeRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        EmployeeTable dataModel = employeeTableList.get(position);
        holder.bind(dataModel);
        holder.itemEmployeeRowBinding.setItemClicked(this);
        Glide.with(context)
                .load(dataModel.getProfileImage())
                .into(holder.itemEmployeeRowBinding.imgProfile);
    }

    @Override
    public int getItemCount() {
        return employeeTableList.size();
    }

    @Override
    public void cardClicked(EmployeeTable item) {
        listener.cardClicked(item);
    }

    public class EmployeeViewHolder extends RecyclerView.ViewHolder {
        public ItemEmployeeRowBinding itemEmployeeRowBinding;
        public EmployeeViewHolder(ItemEmployeeRowBinding itemEmployeeRowBinding) {
            super(itemEmployeeRowBinding.getRoot());
            this.itemEmployeeRowBinding = itemEmployeeRowBinding;
        }

        public void bind(Object object){
            itemEmployeeRowBinding.setVariable(BR.model,object);
            itemEmployeeRowBinding.executePendingBindings();


        }
    }
}
