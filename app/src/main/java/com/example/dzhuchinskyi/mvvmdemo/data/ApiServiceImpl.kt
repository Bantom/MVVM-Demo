package com.example.dzhuchinskyi.mvvmdemo.data

import com.example.dzhuchinskyi.mvvmdemo.data.model.Offer
import com.example.dzhuchinskyi.mvvmdemo.data.model.User
import io.reactivex.Single
import java.util.*

class ApiServiceImpl : ApiService {
    private val minX = 1.0f
    private val maxX = 5.0f

    private val offersList = arrayListOf(
            Offer("1", "Papa John's", "10$ Off", 1F, 8F),
            Offer("2", "Colins", "15$ Off", 3F, 13F),
            Offer("3", "Macs'y", "Get 25$ for 2", 2F, 18F))

    override fun getOffersByUserId(id: Int): Single<List<Offer>> {
        offersList.add(Offer("", "", "", Random().nextFloat() * (maxX - minX) + minX
                , Random().nextFloat() * (maxX - minX) + minX))
        return Single.just(offersList)
    }

    override fun getUser(id: Int): Single<User> {
        return Single.just(User(1, "Denis Zhuchinski", "+380931111111"))
    }
}