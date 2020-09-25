package app.com.whiterabbittest.repository.EmployeeRepository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

import app.com.whiterabbittest.data.local.AppDataBase;
import app.com.whiterabbittest.data.local.EmployeeTable;
import app.com.whiterabbittest.data.remote.RetrofitHelper;
import app.com.whiterabbittest.data.remote.WebServices;
import app.com.whiterabbittest.model.EmployeeResponseModelMain;
import io.reactivex.schedulers.Schedulers;

public class EmployeeRepository {
    private WebServices webServices;
    private AppDataBase appDataBase;

    public EmployeeRepository(Context context) {
        this.webServices = new RetrofitHelper().getEmployeeListApi();
        this.appDataBase = Room.databaseBuilder(context, AppDataBase.class, "employee_list.db").build();;
    }

    public void getEmployeeListFromServer(){
        webServices.getEmployess().subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(this::onSuccess,this::onFailure);
    }

    private void onSuccess(List<EmployeeResponseModelMain> employeeResponseModelMain){
        Log.e("employee_responses",employeeResponseModelMain+"");
        List<EmployeeTable> employeeTables = new ArrayList<>();
        if(employeeResponseModelMain.size()>0){
            for(EmployeeResponseModelMain employeeResponseModelMain1 : employeeResponseModelMain){
                EmployeeTable employeeTable = new EmployeeTable();
                employeeTable.setId(employeeResponseModelMain1.getId());
                employeeTable.setName(employeeResponseModelMain1.getName());
                employeeTable.setProfileImage(employeeResponseModelMain1.getProfileImage() != null?employeeResponseModelMain1.getProfileImage().toString():"");
                employeeTable.setUserName(employeeResponseModelMain1.getUsername());
                employeeTable.setEmail(employeeResponseModelMain1.getEmail());
                employeeTable.setPhone(employeeResponseModelMain1.getPhone() != null?employeeResponseModelMain1.getPhone().toString():"");
                employeeTable.setWebsite(employeeResponseModelMain1.getWebsite() != null?employeeResponseModelMain1.getWebsite().toString():"");
                employeeTable.setStreet(employeeResponseModelMain1.getAddress().getStreet());
                employeeTable.setSuite(employeeResponseModelMain1.getAddress().getSuite());
                employeeTable.setCity(employeeResponseModelMain1.getAddress().getCity());
                employeeTable.setZipcode(employeeResponseModelMain1.getAddress().getZipcode());
                employeeTable.setCompanyName(employeeResponseModelMain1.getCompany() != null?employeeResponseModelMain1.getCompany().getName():"");
                employeeTable.setCatchPhrase(employeeResponseModelMain1.getCompany() != null?employeeResponseModelMain1.getCompany().getCatchPhrase():"");
                employeeTable.setBs(employeeResponseModelMain1.getCompany() != null?employeeResponseModelMain1.getCompany().getBs():"");
                employeeTables.add(employeeTable);
            }

            appDataBase.employeeDao().insertMovie(employeeTables);
        }
    }

    private void onFailure(Throwable throwable){
        throwable.printStackTrace();
    }

    public LiveData<List<EmployeeTable>> getEmployeesFromDb(){
        return appDataBase.employeeDao().getEmployees();
    }

    public LiveData<List<EmployeeTable>> getFilteredEmployeesFromDb(String val){
        return appDataBase.employeeDao().getFilteredEmployes(val);
    }
}
