package com.zozancan.retrofitchallenge.userlist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zozancan.retrofitchallenge.R;
import com.zozancan.retrofitchallenge.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.MyViewHolder> {

    private List<User> userList = new ArrayList<>();

    private OnUserClick userClickListener;

    public UserListAdapter(OnUserClick userClickListener) {
        this.userClickListener = userClickListener;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final User user = userList.get(position);
        holder.userNameTextView.setText(user.getName());
        holder.userMailTextView.setText(user.getEmail());
        holder.userPhoneTextView.setText(user.getPhone());
        holder.userCompanyNameTextView.setText(user.getCompany().getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userClickListener.onUserClick(user);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView userIconImageView;
        private TextView userNameTextView;
        private TextView userPhoneTextView;
        private TextView userMailTextView;
        private TextView userCompanyNameTextView;

        public MyViewHolder(View view) {
            super(view);
            userIconImageView = view.findViewById(R.id.profile_image);
            userNameTextView = view.findViewById(R.id.user_name);
            userPhoneTextView = view.findViewById(R.id.user_phone);
            userMailTextView = view.findViewById(R.id.user_mail);
            userCompanyNameTextView = view.findViewById(R.id.user_company_name);

        }
    }
}