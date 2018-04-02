package com.example.dzhuchinskyi.mvvmdemo.data

import com.example.dzhuchinskyi.mvvmdemo.data.model.Offer
import com.example.dzhuchinskyi.mvvmdemo.data.model.User
import io.reactivex.Single

class DbStorageImpl : DbStorage {
    var offers = emptyList<Offer>()
    var user = User(1, "Denis", "+38050111111")

    override fun getOffersByUserId(userId: Int): Single<List<Offer>> = Single.just(offers)

    override fun getUser(userId: Int): Single<User> = Single.just(user)

    override fun saveOffers(offers: List<Offer>) {
        this.offers = offers
    }

    override fun saveUser(user: User) {
        this.user = user
    }
}