package com.globant.example.mentorapp.presentation.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.globant.example.mentorapp.R;
import com.globant.example.mentorapp.data.entity.UserEntity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by oscar.amezquita on 8/06/2017.
 */

public class ListUsersAdapter extends RecyclerView.Adapter<ListUsersAdapter.ViewHolder> {

    private List<UserEntity> users;
    private Context context;

    public ListUsersAdapter(List<UserEntity> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_users_adapter, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(users.get(position).getLogin());
        if(!users.get(position).getAvatarUrl().isEmpty()) {
            Picasso.with(context).load(users.get(position).getAvatarUrl()).error(R.drawable.ic_not_found).into(holder.userImage);
        }else{
            holder.userImage.setImageResource(R.drawable.ic_not_found);
        }
    }

    @Override
    public int getItemCount() {
        return users != null ? users.size() : -1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;
        public ImageView userImage;


        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.textName);
            userImage = (ImageView) itemView.findViewById(R.id.imageUser);
        }


    }
}
