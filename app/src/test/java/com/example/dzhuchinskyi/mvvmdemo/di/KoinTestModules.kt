package com.example.dzhuchinskyi.mvvmdemo.di

import com.example.dzhuchinskyi.mvvmdemo.data.*
import com.example.dzhuchinskyi.mvvmdemo.domain.TestUserProfileInteractorImpl
import com.example.dzhuchinskyi.mvvmdemo.domain.UserProfileInteractor
import com.example.dzhuchinskyi.mvvmdemo.presentation.UserProfileViewModel
import io.reactivex.schedulers.Schedulers
import org.koin.dsl.module.module

val appModule = module {
    single<ApiService> { TestApiServiceImpl() }
    single<DbStorage> { DbStorageImpl() }
}

val activityModule = module {
    single {
        UserProfileViewModel(get(), Schedulers.trampoline(), Schedulers.trampoline())
    }
}

val userProfileModule = module {
    single<UserRepository> { UserRepositoryImpl(get(), get()) }
    single<UserProfileInteractor> { TestUserProfileInteractorImpl() }
    single<OfferRepository> { OfferRepositoryImpl(get(), get()) }
}
