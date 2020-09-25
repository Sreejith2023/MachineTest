package app.com.whiterabbittest.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;



@Dao
public interface EmployeeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract void insertMovie(List<EmployeeTable> employeeDetails);

    @Query("SELECT * FROM employee_table")
    LiveData<List<EmployeeTable>> getEmployees();

    @Query("SELECT * FROM employee_table WHERE 'name' LIKE :val OR email LIKE :val")
    LiveData<List<EmployeeTable>> getFilteredEmployes(String val);


}
