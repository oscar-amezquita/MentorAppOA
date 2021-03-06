package com.globant.example.mentorapp.subscriberDetails.presentation.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.globant.example.mentorapp.R;
import com.globant.example.mentorapp.subscriberDetails.presentation.model.RepositoryModel;
import com.globant.example.mentorapp.util.Utilities;

import java.util.List;

/**
 * Custom adapter to show user repositories by name and url.
 * Created by oscar.amezquita on 12/07/2017.
 */

public class UserRepositoriesAdapter extends RecyclerView.Adapter<UserRepositoriesAdapter.ViewHolder> {

    private List<RepositoryModel> userRepositoriesAdapterList;

    public UserRepositoriesAdapter(List<RepositoryModel> userRepositoriesAdapterList) {
        this.userRepositoriesAdapterList = userRepositoriesAdapterList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_repository_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RepositoryModel model = userRepositoriesAdapterList.get(position);
        holder.repoNameTextView.setText(model.getRepoName());
        holder.repoURLTextView.setText(model.getRepoURL());
    }

    @Override
    public int getItemCount() {
        return userRepositoriesAdapterList != null ? userRepositoriesAdapterList.size() : Utilities.ZERO_CONSTANT;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView repoNameTextView;
        private TextView repoURLTextView;

        ViewHolder(View itemView) {
            super(itemView);
            repoNameTextView = (TextView) itemView.findViewById(R.id.repoName);
            repoURLTextView = (TextView) itemView.findViewById(R.id.repoURL);
        }
    }
}
