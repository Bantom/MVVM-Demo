package com.example.dzhuchinskyi.mvvmdemo.domain

import com.example.dzhuchinskyi.mvvmdemo.data.model.Offer
import com.example.dzhuchinskyi.mvvmdemo.data.model.User
import io.reactivex.Single

/**
 * Created by dzhuchinskyi on 23.03.18.
 */
interface UserProfileInteractor {

    fun getUserById(userId: Int): Single<User>

    fun getOffersByUserId(userId: Int): Single<List<Offer>>

    fun getUserDataModel(userId: Int): Single<UserDataModel>
}