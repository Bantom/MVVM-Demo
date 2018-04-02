package com.example.dzhuchinskyi.mvvmdemo.data

import com.example.dzhuchinskyi.mvvmdemo.data.model.Offer
import io.reactivex.Single

/**
 * Created by dzhuchinskyi on 23.03.18.
 */
interface OfferRepository {
    fun getOffersByUserId(userId: Int, fresh: Boolean): Single<List<Offer>>
}