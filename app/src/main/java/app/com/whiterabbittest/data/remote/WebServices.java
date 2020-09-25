package app.com.whiterabbittest.data.remote;

import java.util.List;

import app.com.whiterabbittest.model.EmployeeResponseModelMain;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface WebServices {
    @GET("5d565297300000680030a986")
    Observable<List<EmployeeResponseModelMain>> getEmployess();
}
