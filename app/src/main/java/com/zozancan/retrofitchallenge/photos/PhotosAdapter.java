package com.zozancan.retrofitchallenge.photos;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.zozancan.retrofitchallenge.R;
import com.zozancan.retrofitchallenge.model.Photos;

import java.util.ArrayList;
import java.util.List;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.MyViewHolder> {

    String thumbnailUrl;
    Context context;
    private List<Photos> photosList = new ArrayList<>();
    private OnPhotosClick photosClickListener;

    public PhotosAdapter(OnPhotosClick photosClickListener) {
        this.photosClickListener = photosClickListener;
    }

    public void setPhotosList(List<Photos> photosList) {
        this.photosList = photosList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PhotosAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        context = parent.getContext();

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_photos, parent, false);

        return new PhotosAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Photos photos = photosList.get(position);


        Picasso.with(context).load(photos.getThumbnailUrl()).into(holder.photosIconImageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photosClickListener.onPhotosClick(photos);
            }
        });

    }

    @Override
    public int getItemCount() {

        return photosList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView photosIconImageView;


        public MyViewHolder(View view) {
            super(view);
            photosIconImageView = view.findViewById(R.id.photos_image);

        }
    }
}
