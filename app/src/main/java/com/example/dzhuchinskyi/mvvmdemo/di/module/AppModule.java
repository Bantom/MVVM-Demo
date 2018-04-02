package com.example.dzhuchinskyi.mvvmdemo.di.module;

import com.example.dzhuchinskyi.mvvmdemo.data.ApiService;
import com.example.dzhuchinskyi.mvvmdemo.data.ApiServiceImpl;
import com.example.dzhuchinskyi.mvvmdemo.data.DbStorage;
import com.example.dzhuchinskyi.mvvmdemo.data.DbStorageImpl;
import com.example.dzhuchinskyi.mvvmdemo.data.UserRepository;
import com.example.dzhuchinskyi.mvvmdemo.data.UserRepositoryImpl;
import com.example.dzhuchinskyi.mvvmdemo.di.scope.ViewScope;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dzhuchinskyi on 26.03.18.
 */

@Module
public class AppModule {
    @Provides
    @Named("Api")
    ApiService provideApi() {
        return new ApiServiceImpl();
    }

    @Provides
    @Named("Database")
    DbStorage provideDatabase() {
        return new DbStorageImpl();
    }
}
