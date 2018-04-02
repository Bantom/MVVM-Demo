package com.example.dzhuchinskyi.mvvmdemo.di.module;

import com.example.dzhuchinskyi.mvvmdemo.domain.UserProfileInteractor;
import com.example.dzhuchinskyi.mvvmdemo.presentation.UserProfileViewModel;
import com.example.dzhuchinskyi.mvvmdemo.presentation.UserProfileViewModelFactory;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dzhuchinskyi on 27.03.18.
 */
@Module(includes = {UserProfileModule.class})
public class ActivityModule {
    @Provides
    UserProfileViewModelFactory provideProfileViewModelFactory(UserProfileInteractor userProfileInteractor) {
        return new UserProfileViewModelFactory(userProfileInteractor, Schedulers.io(), AndroidSchedulers.mainThread());
    }
}
