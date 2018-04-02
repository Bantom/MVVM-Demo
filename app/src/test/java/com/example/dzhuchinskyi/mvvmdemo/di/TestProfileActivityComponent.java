package com.example.dzhuchinskyi.mvvmdemo.di;

import com.example.dzhuchinskyi.mvvmdemo.presentation.UserProfileViewModel;
import com.example.dzhuchinskyi.mvvmdemo.presentation.UserProfileViewModelTest;

import dagger.Component;

/**
 * Created by dzhuchinskyi on 27.03.18.
 */

@Component(dependencies = TestUserProfileComponent.class, modules = TestActivityModule.class)
public interface TestProfileActivityComponent {

    UserProfileViewModel provideProfileViewModel();

    void inject(UserProfileViewModelTest profileActivity);

}
