package com.example.dzhuchinskyi.mvvmdemo.domain

import android.util.Log
import com.example.dzhuchinskyi.mvvmdemo.data.model.Offer
import com.example.dzhuchinskyi.mvvmdemo.data.OfferRepository
import com.example.dzhuchinskyi.mvvmdemo.data.model.User
import com.example.dzhuchinskyi.mvvmdemo.data.UserRepository
import com.example.dzhuchinskyi.mvvmdemo.di.DaggerTestUserProfileComponent
import com.example.dzhuchinskyi.mvvmdemo.di.TestAppModule
import com.example.dzhuchinskyi.mvvmdemo.di.TestUserProfileComponent
import com.example.dzhuchinskyi.mvvmdemo.di.TestUserProfileModule
import com.example.dzhuchinskyi.mvvmdemo.presentation.UserProfileActivity
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.functions.Function
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class TestUserProfileInteractorImpl : UserProfileInteractor {
    @Inject
    lateinit var userRepository: UserRepository
    @Inject
    lateinit var offerRepository: OfferRepository

    private var lastUpdatedMs = 0L
    private var needsUpdates = false

    val userProfileComponent: TestUserProfileComponent = DaggerTestUserProfileComponent.builder()
            .testAppModule(TestAppModule())
            .testUserProfileModule(TestUserProfileModule())
            .build()

    init {
        userProfileComponent.inject(this)
    }


    override fun getOffersByUserId(id: Int): Single<List<Offer>> {

        needsUpdates = System.currentTimeMillis() > (lastUpdatedMs + 5000)

        if (needsUpdates) lastUpdatedMs = System.currentTimeMillis()

        Log.i(UserProfileActivity.TAG, "needsUpdates: $needsUpdates")

        return offerRepository.getOffersByUserId(id, needsUpdates)
    }

    override fun getUserById(id: Int): Single<User> {
        return userRepository.getUser(id, true)
    }

    override fun getUserDataModel(userId: Int): Single<UserDataModel> {

        needsUpdates = System.currentTimeMillis() > (lastUpdatedMs + 5000)

        if (needsUpdates) {
            lastUpdatedMs = System.currentTimeMillis()
        }

        return userRepository.getUser(21, needsUpdates)
                .flatMap(Function<User, SingleSource<UserDataModel>> { user ->
                    offerRepository
                            .getOffersByUserId(user.id, needsUpdates)
                            .zipWith(Single.just(user), BiFunction { offers, (_, name, phone) ->
                                var totalSaved = 0F
                                for (i in 0 until offers.size) {
                                    totalSaved += offers[i].saved
                                }
                                UserDataModel(user.id, name, phone, offers, totalSaved)
                            })
                })
    }
}