package com.zozancan.retrofitchallenge.userlist;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.zozancan.retrofitchallenge.R;
import com.zozancan.retrofitchallenge.album.AlbumActivity;
import com.zozancan.retrofitchallenge.model.User;
import com.zozancan.retrofitchallenge.network.ApiService;
import com.zozancan.retrofitchallenge.network.RetrofitChallengeApplication;
import com.zozancan.retrofitchallenge.postlist.PostListActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements OnUserClick {

    private ApiService service;

    private List<User> userList;

    private UserListAdapter adapter;

    public static final String USER_KEY = "USER_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        service = ((RetrofitChallengeApplication) getApplication()).getService();

        RecyclerView list = findViewById(R.id.user_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);
        adapter = new UserListAdapter(this);
        list.setAdapter(adapter);
        toastMessage();

        getUsers();
    }

    private void getUsers() {
        service.listRepos().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                userList = response.body();
                displayUsers();
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }

    private void displayUsers() {
        adapter.setUserList(userList);
    }

    @Override
    public void onUserClick(final User user) {
        new AlertDialog.Builder(this)
                .setTitle("Lütfen Seçim Yapınız!")
                .setMessage("Message")
                .setPositiveButton("Albüm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, AlbumActivity.class);
                        intent.putExtra(USER_KEY, user);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Gönderi", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, PostListActivity.class);
                        intent.putExtra(USER_KEY, user);
                        startActivity(intent);
                    }
                }).create().show();
    }

    private void toastMessage() {
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        boolean isFirst = sharedPref.getBoolean("isFirst", true);
        if (isFirst) {
            Toast.makeText(getApplicationContext(),
                    "Uygulamaya ilk defa girdiniz!", Toast.LENGTH_SHORT).show();
        }
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("isFirst", false);
        editor.apply();
    }
}
