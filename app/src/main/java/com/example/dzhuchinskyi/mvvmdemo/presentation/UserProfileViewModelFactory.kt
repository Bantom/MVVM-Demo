package com.example.dzhuchinskyi.mvvmdemo.presentation

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.dzhuchinskyi.mvvmdemo.domain.UserProfileInteractor
import io.reactivex.Scheduler


class UserProfileViewModelFactory(private var userProfileInteractor: UserProfileInteractor
                                  , private var processScheduler: Scheduler
                                  , private var androidScheduler: Scheduler) : ViewModelProvider.Factory {
    
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserProfileViewModel::class.java)) {
            return UserProfileViewModel(userProfileInteractor, processScheduler, androidScheduler) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}