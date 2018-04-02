package com.example.dzhuchinskyi.mvvmdemo.data

import com.example.dzhuchinskyi.mvvmdemo.data.model.User
import io.reactivex.Single

/**
 * Created by dzhuchinskyi on 23.03.18.
 */
interface UserRepository {
    fun getUser(userId: Int, fresh: Boolean): Single<User>
}