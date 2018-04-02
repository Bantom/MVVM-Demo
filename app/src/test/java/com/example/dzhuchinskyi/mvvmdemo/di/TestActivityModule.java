package com.example.dzhuchinskyi.mvvmdemo.di;

import com.example.dzhuchinskyi.mvvmdemo.domain.UserProfileInteractor;
import com.example.dzhuchinskyi.mvvmdemo.presentation.UserProfileViewModel;

import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dzhuchinskyi on 27.03.18.
 */
@Module
public class TestActivityModule {
    @Provides
    UserProfileViewModel provideProfileViewModel(UserProfileInteractor userInteractor) {
        return new UserProfileViewModel(userInteractor, Schedulers.trampoline(), Schedulers.trampoline());
    }
}
