package com.example.dzhuchinskyi.mvvmdemo.di.component;

import com.example.dzhuchinskyi.mvvmdemo.di.module.ActivityModule;
import com.example.dzhuchinskyi.mvvmdemo.presentation.UserProfileActivity;
import com.example.dzhuchinskyi.mvvmdemo.presentation.UserProfileViewModelFactory;

import dagger.Component;

/**
 * Created by dzhuchinskyi on 27.03.18.
 */

@Component(modules = ActivityModule.class)
public interface ProfileActivityComponent {

    UserProfileViewModelFactory provideProfileViewModelFactory();

    void inject(UserProfileActivity profileActivity);
}
