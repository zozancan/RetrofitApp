package com.zozancan.retrofitchallenge.postlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zozancan.retrofitchallenge.R;
import com.zozancan.retrofitchallenge.model.User;
import com.zozancan.retrofitchallenge.userlist.MainActivity;

public class PostListActivity extends AppCompatActivity {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_list);

        Intent intent = getIntent();
        if (intent != null) {
            user = intent.getParcelableExtra(MainActivity.USER_KEY);
        }
    }
}
