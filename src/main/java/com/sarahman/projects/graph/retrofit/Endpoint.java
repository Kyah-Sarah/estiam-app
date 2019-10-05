package com.sarahman.projects.graph.retrofit;

import com.sarahman.projects.graph.models.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Endpoint {

    String BASE_URL = "https://opendata.paris.fr/api/records/1.0/";

    @GET("search")
    Call<ApiResponse> getDataSet(@Query("dataset") String dataSet, @Query("rows") int rows);
}
