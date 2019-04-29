package com.zozancan.retrofitchallenge.postlist;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zozancan.retrofitchallenge.R;
import com.zozancan.retrofitchallenge.model.Post;
import com.zozancan.retrofitchallenge.network.ApiService;
import com.zozancan.retrofitchallenge.network.RetrofitChallengeApplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostListActivity extends AppCompatActivity implements OnPostClick{

    private ApiService service;

    private List<Post> postList;

    private PostListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_list);
        service = ((RetrofitChallengeApplication) getApplication()).getService();

        RecyclerView list = findViewById(R.id.post_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);
        adapter = new PostListAdapter(this);
        list.setAdapter(adapter);

        getPosts();
    }

    private void getPosts() {
        service.listPosts().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                postList = response.body();
                displayPosts();
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
    }

    private void displayPosts() { adapter.setPostList(postList); }

    @Override
    public void onPostClick(Post post) {

    }
}
