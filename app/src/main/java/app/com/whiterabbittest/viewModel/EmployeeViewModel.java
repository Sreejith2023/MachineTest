package app.com.whiterabbittest.viewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import app.com.whiterabbittest.data.local.EmployeeTable;
import app.com.whiterabbittest.repository.EmployeeRepository.EmployeeRepository;


public class EmployeeViewModel extends ViewModel {
    private EmployeeRepository mEmployeeRepository;

    public EmployeeViewModel() {
    }

    public EmployeeViewModel(Context context) {
        this.mEmployeeRepository = new EmployeeRepository(context);
    }

    public void getEmployeeListFromServer(){
        mEmployeeRepository.getEmployeeListFromServer();
    }

    public LiveData<List<EmployeeTable>> getEmployess(){
        return mEmployeeRepository.getEmployeesFromDb();
    }

    public LiveData<List<EmployeeTable>> getFilteredEmployess(String val){
        return mEmployeeRepository.getFilteredEmployeesFromDb(val);
    }


}
