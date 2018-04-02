package com.example.dzhuchinskyi.mvvmdemo.di.module;

import com.example.dzhuchinskyi.mvvmdemo.data.ApiService;
import com.example.dzhuchinskyi.mvvmdemo.data.DbStorage;
import com.example.dzhuchinskyi.mvvmdemo.data.OfferRepository;
import com.example.dzhuchinskyi.mvvmdemo.data.OfferRepositoryImpl;
import com.example.dzhuchinskyi.mvvmdemo.data.UserRepository;
import com.example.dzhuchinskyi.mvvmdemo.data.UserRepositoryImpl;
import com.example.dzhuchinskyi.mvvmdemo.domain.UserProfileInteractor;
import com.example.dzhuchinskyi.mvvmdemo.domain.UserProfileInteractorImpl;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dzhuchinskyi on 26.03.18.
 */

@Module(includes = AppModule.class)
public class UserProfileModule {
    @Provides
    UserRepository provideUserRepository(@Named("Api") ApiService apiService, @Named("Database") DbStorage dbStorage) {
        return new UserRepositoryImpl(apiService, dbStorage);
    }

    @Provides
    UserProfileInteractor provideUserProfileInteractor() {
        return new UserProfileInteractorImpl();
    }

    @Provides
    OfferRepository provideOfferRepository(@Named("Api") ApiService apiService, @Named("Database") DbStorage dbStorage) {
        return new OfferRepositoryImpl(apiService, dbStorage);
    }
}
