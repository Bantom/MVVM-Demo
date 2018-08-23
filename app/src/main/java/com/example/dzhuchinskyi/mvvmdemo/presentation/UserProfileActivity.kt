package com.example.dzhuchinskyi.mvvmdemo.presentation

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.example.dzhuchinskyi.mvvmdemo.R
import com.example.dzhuchinskyi.mvvmdemo.databinding.ActivityProfileBinding
import org.koin.android.ext.android.inject

class UserProfileActivity : AppCompatActivity() {

    private val userProfileViewModelFactory: UserProfileViewModelFactory by inject()

    lateinit var activityUserProfileBinding: ActivityProfileBinding

    private lateinit var userProfileViewModel: UserProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userProfileViewModel = ViewModelProviders.of(this, userProfileViewModelFactory).get(UserProfileViewModel::class.java)

        activityUserProfileBinding = DataBindingUtil.setContentView(this, R.layout.activity_profile)
        activityUserProfileBinding.viewModel = userProfileViewModel
        userProfileViewModel.updateData(1)

        userProfileViewModel.getUserDataModel()
                .observe(this, Observer { userDataModel ->
                    Log.i(TAG, "userDataModel: " + userDataModel!!.userName +
                            ", " + userDataModel.userPhone)
                    activityUserProfileBinding.setDataModel(userDataModel)
                })

        userProfileViewModel.getError().observe(this, Observer { throwable -> Log.i(TAG, "Throwable: " + throwable!!) })
    }

    companion object {
        var TAG = "Demo"
    }
}
