package com.example.dzhuchinskyi.mvvmdemo.di.component;

import com.example.dzhuchinskyi.mvvmdemo.data.OfferRepository;
import com.example.dzhuchinskyi.mvvmdemo.data.UserRepository;
import com.example.dzhuchinskyi.mvvmdemo.di.module.UserProfileModule;
import com.example.dzhuchinskyi.mvvmdemo.domain.UserProfileInteractor;
import com.example.dzhuchinskyi.mvvmdemo.domain.UserProfileInteractorImpl;

import dagger.Component;

/**
 * Created by dzhuchinskyi on 27.03.18.
 */

@Component(modules = UserProfileModule.class)
public interface UserProfileComponent {

    UserRepository provideUserRepository();

    OfferRepository provideOfferRepository();

    UserProfileInteractor provideUserProfileInteractor();

    void inject(UserProfileInteractorImpl userProfileInteractor);
}
