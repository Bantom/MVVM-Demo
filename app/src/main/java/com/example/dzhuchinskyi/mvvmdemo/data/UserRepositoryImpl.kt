package com.example.dzhuchinskyi.mvvmdemo.data

import com.example.dzhuchinskyi.mvvmdemo.data.model.User
import io.reactivex.Single

class UserRepositoryImpl(private val apiService: ApiService, private val dbStorage: DbStorage) : UserRepository {

    override fun getUser(userId: Int, fresh: Boolean): Single<User> {
        return if (fresh) {
            apiService.getUser(userId)
                    .doOnSuccess { user -> dbStorage.saveUser(user) }
        } else {
            dbStorage.getUser(userId)
        }
    }
}