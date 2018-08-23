package com.example.dzhuchinskyi.mvvmdemo.di

import com.example.dzhuchinskyi.mvvmdemo.data.*
import com.example.dzhuchinskyi.mvvmdemo.domain.UserProfileInteractor
import com.example.dzhuchinskyi.mvvmdemo.domain.UserProfileInteractorImpl
import com.example.dzhuchinskyi.mvvmdemo.presentation.UserProfileViewModelFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.dsl.module.module

val appModule = module {
    single<ApiService> { ApiServiceImpl() }
    single<DbStorage> { DbStorageImpl() }
}

val activityModule = module {
    single {
        UserProfileViewModelFactory(get(), Schedulers.io(), AndroidSchedulers.mainThread())
    }
}

val userProfileModule = module {
    single<UserRepository> { UserRepositoryImpl(get(), get()) }
    single<UserProfileInteractor> { UserProfileInteractorImpl() }
    single<OfferRepository> { OfferRepositoryImpl(get(), get()) }
}
