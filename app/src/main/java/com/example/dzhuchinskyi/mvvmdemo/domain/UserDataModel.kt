package com.example.dzhuchinskyi.mvvmdemo.domain

import com.example.dzhuchinskyi.mvvmdemo.data.model.Offer

data class UserDataModel(val userId: Int, val userName: String, val userPhone: String, val offers: List<Offer>, val totalSaved: Float)
