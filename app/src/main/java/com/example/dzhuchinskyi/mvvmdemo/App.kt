package com.example.dzhuchinskyi.mvvmdemo

import android.app.Application
import com.example.dzhuchinskyi.mvvmdemo.di.activityModule
import com.example.dzhuchinskyi.mvvmdemo.di.appModule
import com.example.dzhuchinskyi.mvvmdemo.di.userProfileModule
import org.koin.android.ext.android.startKoin

/**
 * Created by dzhuc on 27.03.18.
 */

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(appModule, activityModule, userProfileModule))

    }
}
