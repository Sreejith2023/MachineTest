package app.com.whiterabbittest.screens.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import app.com.whiterabbittest.R;
import app.com.whiterabbittest.data.local.EmployeeTable;
import app.com.whiterabbittest.databinding.HomeMainBinding;
import app.com.whiterabbittest.model.CustomClickListener;
import app.com.whiterabbittest.screens.DetailActivity;
import app.com.whiterabbittest.screens.home.adapter.HomeEmployeeListAdapter;
import app.com.whiterabbittest.viewModel.EmployeeViewModel;

public class HomeActivity extends AppCompatActivity implements CustomClickListener {
    HomeMainBinding binding;
    EmployeeViewModel employeeViewModel;
    HomeEmployeeListAdapter homeEmployeeListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.home_main);
        employeeViewModel = ViewModelProviders.of(this).get(EmployeeViewModel.class);
        employeeViewModel = new EmployeeViewModel(this);
        getEmployeesFromDb();
        getEmployeeDataFromServer();
        onSearchValue();
    }

    private void onSearchValue() {
        binding.search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                employeeViewModel.getFilteredEmployess(s).observe(HomeActivity.this, employeeTables -> {
                    Log.e("employees_db",employeeTables+"");
                    if(employeeTables.size()>0){
                       homeEmployeeListAdapter.notifyDataSetChanged();
                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
            //    Toast.makeText(HomeActivity.this, ""+s, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    private void getEmployeesFromDb() {
        employeeViewModel.getEmployess().observe(this, employeeTables -> {
            Log.e("employees_db",employeeTables+"");
            if(employeeTables.size()>0){
                homeEmployeeListAdapter = new HomeEmployeeListAdapter(employeeTables, this, this);
                binding.setAdapter(homeEmployeeListAdapter);
            }
        });
    }



    private void getEmployeeDataFromServer() {
        employeeViewModel.getEmployeeListFromServer();
    }

    @Override
    public void cardClicked(EmployeeTable item) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("emp_values",item);
        startActivity(intent);
    }
}