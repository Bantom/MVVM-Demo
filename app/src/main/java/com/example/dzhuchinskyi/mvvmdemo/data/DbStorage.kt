package com.example.dzhuchinskyi.mvvmdemo.data

import com.example.dzhuchinskyi.mvvmdemo.data.model.Offer
import com.example.dzhuchinskyi.mvvmdemo.data.model.User
import io.reactivex.Single

/**
 * Created by dzhuchinskyi on 23.03.18.
 */
interface DbStorage {
    fun getUser(id: Int): Single<User>

    fun saveUser(user: User)

    fun getOffersByUserId(id: Int): Single<List<Offer>>

    fun saveOffers(offers: List<Offer>)

}