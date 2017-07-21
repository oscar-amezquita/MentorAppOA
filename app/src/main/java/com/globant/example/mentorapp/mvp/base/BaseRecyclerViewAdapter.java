package com.globant.example.mentorapp.mvp.base;

/**
 * Generic class to define basic RecycleView behavior
 * Created by oscar.amezquita on 21/07/2017.
 */

public class BaseRecyclerViewAdapter {

    /**
     * Listener to handle selected User click.
     */
    public interface onUserClick {
        void onUserSelected(String userId);
    }
}
