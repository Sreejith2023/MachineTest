package app.com.whiterabbittest.screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.bumptech.glide.Glide;

import app.com.whiterabbittest.R;
import app.com.whiterabbittest.data.local.EmployeeTable;
import app.com.whiterabbittest.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {
    ActivityDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        getIntentDatas();
    }

    private void getIntentDatas() {
        Intent intent = getIntent();
        EmployeeTable employeeTable = (EmployeeTable) intent.getSerializableExtra("emp_values");
        Log.e("employee_values", employeeTable+"");
        Glide.with(this)
                .load(employeeTable.getProfileImage())
                .into(binding.imgProfile);
        binding.setModel(employeeTable);
    }
}