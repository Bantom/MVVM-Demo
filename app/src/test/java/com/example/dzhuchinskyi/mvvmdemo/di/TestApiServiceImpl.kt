package com.example.dzhuchinskyi.mvvmdemo.di

import com.example.dzhuchinskyi.mvvmdemo.data.ApiService
import com.example.dzhuchinskyi.mvvmdemo.data.model.Offer
import com.example.dzhuchinskyi.mvvmdemo.data.model.User
import io.reactivex.Single

class TestApiServiceImpl : ApiService {

    private val offersList = arrayListOf(
            Offer("1", "Papa John's", "10$ Off", 1F, 8F))

    override fun getOffersByUserId(id: Int): Single<List<Offer>> {
        return Single.just(offersList)
    }

    override fun getUser(id: Int): Single<User> {
        return Single.just(User(1, "Denis Zhuchinski", "+380936789367"))
    }
}