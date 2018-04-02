package com.example.dzhuchinskyi.mvvmdemo.presentation

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.example.dzhuchinskyi.mvvmdemo.domain.UserDataModel
import com.example.dzhuchinskyi.mvvmdemo.domain.UserProfileInteractor
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by dzhuchinskyi on 26.03.18.
 */

class UserProfileViewModel(private val userProfileInteractor: UserProfileInteractor
                           , private val processScheduler: Scheduler
                           , private val androidScheduler: Scheduler) : ViewModel() {
    private val errorObservable = MutableLiveData<Throwable>()
    private val profileDataModelObservable = MutableLiveData<UserDataModel>()

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun getUserDataModel(): LiveData<UserDataModel> {
        return profileDataModelObservable
    }

    fun getError(): LiveData<Throwable> {
        return errorObservable
    }

    fun onUpdateClicked(user: UserDataModel) {
        updateData(user.userId)
    }

    fun updateData(userId: Int) {
        val subscription = userProfileInteractor
                .getUserDataModel(userId)
                .subscribeOn(processScheduler)
                .observeOn(androidScheduler)
                .subscribe({ dataModel -> profileDataModelObservable.postValue(dataModel) }
                        , { error -> errorObservable.postValue(error) })

        compositeDisposable.add(subscription)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}