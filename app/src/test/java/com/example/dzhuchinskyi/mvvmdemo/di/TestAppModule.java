package com.example.dzhuchinskyi.mvvmdemo.di;

import com.example.dzhuchinskyi.mvvmdemo.data.ApiService;
import com.example.dzhuchinskyi.mvvmdemo.data.DbStorage;
import com.example.dzhuchinskyi.mvvmdemo.data.DbStorageImpl;
import com.example.dzhuchinskyi.mvvmdemo.di.module.AppModule;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dzhuchinskyi on 26.03.18.
 */

@Module
public class TestAppModule extends AppModule {
    @Provides
    @Named("TestApi")
    ApiService provideApi() {
        return new TestApiServiceImpl();
    }

    @Provides
    @Named("TestDatabase")
    DbStorage provideDatabase() {
        return new DbStorageImpl();
    }
}
