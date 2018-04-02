package com.example.dzhuchinskyi.mvvmdemo.data

import com.example.dzhuchinskyi.mvvmdemo.data.model.Offer
import com.example.dzhuchinskyi.mvvmdemo.data.model.User
import io.reactivex.Single

/**
 * Created by dzhuchinskyi on 23.03.18.
 */
interface ApiService {
    fun getUser(id: Int): Single<User>

    fun getOffersByUserId(id: Int): Single<List<Offer>>
}