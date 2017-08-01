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
import com.globant.example.mentorapp.home.presentation.view.activity.HomeScreenActivity;
import com.globant.example.mentorapp.home.presentation.view.fragment.ListUsersViewFragment;
import com.globant.example.mentorapp.util.Utilities;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Custom adapter to show General data fro users.
 * Created by oscar.amezquita on 8/06/2017.
 */

public class ListUsersAdapter extends RecyclerView.Adapter<ListUsersAdapter.ViewHolder> {

    private List<ModelUserEntity> users;
    private Context context;
    private ListUsersViewFragment fragmentListener;

    public ListUsersAdapter(List<ModelUserEntity> users) {
        this.users = users;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_users_adapter, null);
        context = parent.getContext();
        fragmentListener = (ListUsersViewFragment) ((HomeScreenActivity) context).getSupportFragmentManager().findFragmentByTag(ListUsersViewFragment.LIST_TAG);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.user = users.get(position);
        holder.userNameTextView.setText(holder.user.getName());
        Picasso.with(context).load(holder.user.getImageUrl())
                .error(R.drawable.ic_not_found)
                .placeholder(R.drawable.ic_placeholder)
                .into(holder.userImage);
    }

    @Override
    public int getItemCount() {
        return users != null ? users.size() : Utilities.ZERO_CONSTANT;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView userNameTextView;
        private ImageView userImage;
        private ModelUserEntity user;

        ViewHolder(View itemView) {
            super(itemView);
            userNameTextView = (TextView) itemView.findViewById(R.id.textName);
            userImage = (ImageView) itemView.findViewById(R.id.imageUser);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fragmentListener.onUserSelected(user.getName());
                }
            });
        }

    }

}

