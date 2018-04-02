package com.example.dzhuchinskyi.mvvmdemo.data

import com.example.dzhuchinskyi.mvvmdemo.data.model.Offer
import io.reactivex.Single

/**
 * Created by dzhuchinskyi on 23.03.18.
 */
class OfferRepositoryImpl(private val apiService: ApiService, private val dbStorage: DbStorage) : OfferRepository {

    override fun getOffersByUserId(userId: Int, fresh: Boolean): Single<List<Offer>> {
        return if (fresh) {
            apiService.getOffersByUserId(userId)
                    .doOnSuccess { offers -> dbStorage.saveOffers(offers) }
        } else {
            dbStorage.getOffersByUserId(userId)
        }
    }
}