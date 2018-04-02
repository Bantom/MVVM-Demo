package com.example.dzhuchinskyi.mvvmdemo;

import android.app.Application;
import android.content.Context;

import com.example.dzhuchinskyi.mvvmdemo.di.component.DaggerProfileActivityComponent;
import com.example.dzhuchinskyi.mvvmdemo.di.component.DaggerUserProfileComponent;
import com.example.dzhuchinskyi.mvvmdemo.di.component.ProfileActivityComponent;
import com.example.dzhuchinskyi.mvvmdemo.di.component.UserProfileComponent;
import com.example.dzhuchinskyi.mvvmdemo.di.module.ActivityModule;
import com.example.dzhuchinskyi.mvvmdemo.di.module.AppModule;
import com.example.dzhuchinskyi.mvvmdemo.di.module.UserProfileModule;

/**
 * Created by dzhuc on 27.03.18.
 */

public class App extends Application {
    private static ProfileActivityComponent profileActivityComponent;
    private static UserProfileComponent userProfileComponent;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static ProfileActivityComponent getProfileActivityComponent() {
        if (profileActivityComponent == null) {
            profileActivityComponent = DaggerProfileActivityComponent
                    .builder()
                    .activityModule(new ActivityModule())
                    .userProfileModule(new UserProfileModule())
                    .build();
        }
        return profileActivityComponent;
    }

    public static UserProfileComponent getUserProfileComponent() {
        if (userProfileComponent == null) {
            userProfileComponent = DaggerUserProfileComponent
                    .builder()
                    .appModule(new AppModule())
                    .userProfileModule(new UserProfileModule())
                    .build();
        }
        return userProfileComponent;
    }
}
