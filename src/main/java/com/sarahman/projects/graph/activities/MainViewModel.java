package com.sarahman.projects.graph.activities;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sarahman.projects.graph.models.ApiResponse;
import com.sarahman.projects.graph.retrofit.ApiClient;
import com.sarahman.projects.graph.retrofit.Endpoint;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class MainViewModel extends ViewModel {

    private MutableLiveData<Boolean> loading;
    private MutableLiveData<ApiResponse> apiResponse;

    public LiveData<Boolean> getLoading() {
        if (loading == null) loading = new MutableLiveData<>();
        loading.setValue(false);
        return loading;
    }

    public LiveData<ApiResponse> getApiResponse() {
        if (apiResponse == null) apiResponse = new MutableLiveData<>();
        return apiResponse;
    }

    public void loadGraph(final Context context) {
        loading.setValue(true);

        Endpoint api = ApiClient.getClient(context).create(Endpoint.class);

        Call<ApiResponse> call = api.getDataSet("velib-disponibilite-en-temps-reel", 6);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                loading.setValue(false);
                if (response.isSuccessful()) {
                    apiResponse.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                loading.setValue(false);
                Toast.makeText(context, "Network Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
