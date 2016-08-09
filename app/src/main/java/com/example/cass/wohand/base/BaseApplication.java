package com.example.cass.wohand.base;

import android.app.Activity;
import android.app.Application;

import com.example.cass.wohand.beans.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cass on 2016/8/3.
 */
public class BaseApplication extends Application {
    private User user;
    private List<Activity> activities;

    @Override
    public void onCreate() {
        super.onCreate();

        init();
    }

    private void init() {
        activities = new ArrayList<>();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void finish() {
        for (Activity activity : activities) {
            activity.finish();
        }
    }
}

