package com.example.dzhuchinskyi.mvvmdemo.domain

import com.example.dzhuchinskyi.mvvmdemo.data.model.Offer
import com.example.dzhuchinskyi.mvvmdemo.data.OfferRepository
import com.example.dzhuchinskyi.mvvmdemo.data.model.User
import com.example.dzhuchinskyi.mvvmdemo.data.UserRepository
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.functions.Function
import io.reactivex.functions.BiFunction
import org.koin.java.standalone.KoinJavaComponent.inject

/**
 * Handles business logic ensuring that the latest data is exposed
 * to the upper layer
 */

class UserProfileInteractorImpl : UserProfileInteractor {

    private val userRepository: UserRepository by inject(UserRepository::class.java)
    private val offerRepository: OfferRepository by inject(OfferRepository::class.java)

    private val timeout = 5000
    private var lastUpdatedMs = 0L
    private var needsUpdate = false

    override fun getOffersByUserId(userId: Int): Single<List<Offer>> {
        needsUpdate = System.currentTimeMillis() > (lastUpdatedMs + timeout)

        if (needsUpdate) lastUpdatedMs = System.currentTimeMillis()

        return offerRepository.getOffersByUserId(userId, needsUpdate)
    }

    override fun getUserById(userId: Int): Single<User> {
        return userRepository.getUser(userId, true)
    }

    override fun getUserDataModel(userId: Int): Single<UserDataModel> {
        needsUpdate = System.currentTimeMillis() > (lastUpdatedMs + 5000)

        if (needsUpdate) {
            lastUpdatedMs = System.currentTimeMillis()
        }

        return userRepository
                .getUser(userId, needsUpdate)
                .flatMap(Function<User, SingleSource<UserDataModel>> { user ->
                    offerRepository
                            .getOffersByUserId(user.id, needsUpdate)
                            .zipWith(Single.just(user),
                                    BiFunction { offers, (_, name, phone) ->
                                        var totalSaved = 0F
                                        for (i in 0 until offers.size) totalSaved += offers[i].saved
                                        UserDataModel(userId, name, phone, offers, totalSaved)
                                    })
                })
    }
}