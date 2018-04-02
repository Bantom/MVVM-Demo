package com.example.dzhuchinskyi.mvvmdemo.di;

import com.example.dzhuchinskyi.mvvmdemo.data.ApiService;
import com.example.dzhuchinskyi.mvvmdemo.data.DbStorage;
import com.example.dzhuchinskyi.mvvmdemo.data.OfferRepository;
import com.example.dzhuchinskyi.mvvmdemo.data.OfferRepositoryImpl;
import com.example.dzhuchinskyi.mvvmdemo.data.UserRepository;
import com.example.dzhuchinskyi.mvvmdemo.data.UserRepositoryImpl;
import com.example.dzhuchinskyi.mvvmdemo.domain.UserProfileInteractor;
import com.example.dzhuchinskyi.mvvmdemo.domain.TestUserProfileInteractorImpl;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dzhuchinskyi on 26.03.18.
 */

@Module(includes = TestAppModule.class)
public class TestUserProfileModule {
    @Provides
    UserRepository provideUserRepository(@Named("TestApi") ApiService apiService, @Named("TestDatabase") DbStorage dbStorage) {
        return new UserRepositoryImpl(apiService, dbStorage);
    }

    @Provides
    OfferRepository provideOfferRepository(@Named("TestApi") ApiService apiService, @Named("TestDatabase") DbStorage dbStorage) {
        return new OfferRepositoryImpl(apiService, dbStorage);
    }

    @Provides
    UserProfileInteractor provideUserProfileInteractor() {
        return new TestUserProfileInteractorImpl();
    }

}
