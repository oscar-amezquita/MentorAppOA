package com.globant.example.mentorapp.home.presentation.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.globant.example.mentorapp.R;
import com.globant.example.mentorapp.home.presentation.model.ModelUserEntity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Custom adapter to show General data fro users.
 * Created by oscar.amezquita on 8/06/2017.
 */

public class ListUsersAdapter extends RecyclerView.Adapter<ListUsersAdapter.ViewHolder> {

    private List<ModelUserEntity> users;
    private Context context;

    public ListUsersAdapter(List<ModelUserEntity> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_users_adapter, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.userNameTextView.setText(users.get(position).getName());
        if (!users.get(position).getImageUrl().isEmpty()) {
            Picasso.with(context).load(users.get(position).getImageUrl()).error(R.drawable.ic_not_found).into(holder.userImage);
        } else {
            holder.userImage.setImageResource(R.drawable.ic_not_found);
        }
    }

    @Override
    public int getItemCount() {
        return users != null ? users.size() : R.integer.zero_constant;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView userNameTextView;
        private ImageView userImage;

        public ViewHolder(View itemView) {
            super(itemView);
            userNameTextView = (TextView) itemView.findViewById(R.id.textName);
            userImage = (ImageView) itemView.findViewById(R.id.imageUser);
        }


    }
}
