package com.zozancan.retrofitchallenge.album;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zozancan.retrofitchallenge.R;
import com.zozancan.retrofitchallenge.model.Album;
import com.zozancan.retrofitchallenge.network.ApiService;
import com.zozancan.retrofitchallenge.network.RetrofitChallengeApplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumActivity extends AppCompatActivity implements OnAlbumClick {

    private ApiService service;

    private List<Album> albumList;

    private AlbumAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        service = ((RetrofitChallengeApplication) getApplication()).getService();

        RecyclerView list = findViewById(R.id.album_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);
        adapter = new AlbumAdapter(this);
        list.setAdapter(adapter);

        getAlbums();
    }

    private void getAlbums() {
        service.listAlbums().enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                albumList = response.body();
                displayPosts();
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }

    private void displayPosts() {
        adapter.setAlbumList(albumList);
    }

    @Override
    public void onAlbumClick(Album album) {

    }
}
