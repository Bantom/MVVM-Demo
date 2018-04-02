package com.example.dzhuchinskyi.mvvmdemo.di;

import com.example.dzhuchinskyi.mvvmdemo.data.OfferRepository;
import com.example.dzhuchinskyi.mvvmdemo.data.UserRepository;
import com.example.dzhuchinskyi.mvvmdemo.domain.UserProfileInteractor;
import com.example.dzhuchinskyi.mvvmdemo.domain.TestUserProfileInteractorImpl;

import dagger.Component;

/**
 * Created by dzhuchinskyi on 27.03.18.
 */

@Component(modules = TestUserProfileModule.class)
public interface TestUserProfileComponent {

    UserRepository provideUserRepository();

    OfferRepository provideOfferRepository();

    UserProfileInteractor provideUserProfileInteractor();

    void inject(TestUserProfileInteractorImpl userProfileInteractor);
}
