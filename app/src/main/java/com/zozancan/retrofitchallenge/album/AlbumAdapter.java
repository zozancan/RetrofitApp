package com.zozancan.retrofitchallenge.album;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zozancan.retrofitchallenge.R;
import com.zozancan.retrofitchallenge.model.Album;

import java.util.ArrayList;
import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.MyViewHolder> {

    private List<Album> albumList = new ArrayList<>();

    private OnAlbumClick albumClickListener;

    public AlbumAdapter(OnAlbumClick albumClickListener) {
        this.albumClickListener = albumClickListener;
    }

    public void setAlbumList(List<Album> albumList) {
        this.albumList = albumList;
        notifyDataSetChanged();
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_album, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Album album = albumList.get(position);
        holder.albumIdTextView.setText(String.valueOf(album.getId()));
        holder.albumTitleTextView.setText(album.getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                albumClickListener.onAlbumClick(album);
            }
        });


    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView albumIconImageView;
        private TextView albumIdTextView;
        private TextView albumTitleTextView;

        public MyViewHolder(View view) {
            super(view);
            albumIconImageView = view.findViewById(R.id.album_image);
            albumIdTextView = view.findViewById(R.id.album_id);
            albumTitleTextView = view.findViewById(R.id.album_title);
        }
    }
}
