package com.zozancan.retrofitchallenge.photos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zozancan.retrofitchallenge.R;
import com.zozancan.retrofitchallenge.model.Photos;
import com.zozancan.retrofitchallenge.network.ApiService;
import com.zozancan.retrofitchallenge.network.RetrofitChallengeApplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotosActivity extends AppCompatActivity {

    private ApiService service;

    private List<Photos> photosList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);
        service = ((RetrofitChallengeApplication) getApplication()).getService();

        getPhotos();
    }

    private void getPhotos() {
        service.listPhotos().enqueue(new Callback<List<Photos>>() {
            @Override
            public void onResponse(Call<List<Photos>> call, Response<List<Photos>> response) {
                photosList = response.body();
            }

            @Override
            public void onFailure(Call<List<Photos>> call, Throwable t) {

            }
        });
    }
}
