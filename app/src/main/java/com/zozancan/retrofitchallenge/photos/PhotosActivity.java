package com.zozancan.retrofitchallenge.photos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zozancan.retrofitchallenge.R;
import com.zozancan.retrofitchallenge.model.Photos;
import com.zozancan.retrofitchallenge.network.ApiService;
import com.zozancan.retrofitchallenge.network.RetrofitChallengeApplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotosActivity extends AppCompatActivity implements OnPhotosClick {

    private ApiService service;

    private List<Photos> photosList;

    private PhotosAdapter adapter;

    public static final String PHOTOS_KEY = "PHOTOS_KEY";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);
        service = ((RetrofitChallengeApplication) getApplication()).getService();

        RecyclerView list = findViewById(R.id.photos_list);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        list.setLayoutManager(gridLayoutManager);
        adapter = new PhotosAdapter(this);
        list.setAdapter(adapter);

        getPhotos();
    }

    private void getPhotos() {
        service.listPhotos().enqueue(new Callback<List<Photos>>() {
            @Override
            public void onResponse(Call<List<Photos>> call, Response<List<Photos>> response) {
                photosList = response.body();
                displayPhotos();
            }

            @Override
            public void onFailure(Call<List<Photos>> call, Throwable t) {

            }
        });
    }

    private void displayPhotos() {
        adapter.setPhotosList(photosList);
    }

    @Override
    public void onPhotosClick(final Photos photos) {
        Intent intent = new Intent(PhotosActivity.this, PhotosDetailActivity.class);
        intent.putExtra(PHOTOS_KEY, photos);
        startActivity(intent);


    }
}
