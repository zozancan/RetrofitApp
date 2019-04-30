package com.zozancan.retrofitchallenge.postlist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zozancan.retrofitchallenge.R;
import com.zozancan.retrofitchallenge.model.Post;

import java.util.ArrayList;
import java.util.List;

public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.MyViewHolder> {

    private List<Post> postList = new ArrayList<>();

    private OnPostClick postClickListener;

    public PostListAdapter(OnPostClick postClickListener) {
        this.postClickListener = postClickListener;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_post, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Post post = postList.get(position);
        holder.postIdTextView.setText(String.valueOf(post.getId()));
        holder.postTitleTextView.setText(post.getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postClickListener.onPostClick(post);
            }
        });

    }

    @Override
    public int getItemCount() {

        return postList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView postIconImageView;
        private TextView postIdTextView;
        private TextView postTitleTextView;

        public MyViewHolder(View view) {
            super(view);
            postIconImageView = view.findViewById(R.id.post_image);
            postIdTextView = view.findViewById(R.id.post_id);
            postTitleTextView = view.findViewById(R.id.post_title);

        }
}
}
