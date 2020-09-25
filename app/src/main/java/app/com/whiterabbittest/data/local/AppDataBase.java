package app.com.whiterabbittest.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {EmployeeTable.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
    public abstract EmployeeDao employeeDao();
}
