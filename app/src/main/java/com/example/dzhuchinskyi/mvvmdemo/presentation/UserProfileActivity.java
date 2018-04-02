package com.example.dzhuchinskyi.mvvmdemo.presentation;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.dzhuchinskyi.mvvmdemo.App;
import com.example.dzhuchinskyi.mvvmdemo.R;
import com.example.dzhuchinskyi.mvvmdemo.databinding.ActivityProfileBinding;
import com.example.dzhuchinskyi.mvvmdemo.domain.UserDataModel;

import javax.inject.Inject;

public class UserProfileActivity extends AppCompatActivity {
    public static String TAG = "Demo";

    ActivityProfileBinding activityUserProfileBinding;

    @Inject
    UserProfileViewModelFactory userProfileViewModelFactory;

    UserProfileViewModel userProfileViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getProfileActivityComponent().inject(this);

        userProfileViewModel = ViewModelProviders.of(this, userProfileViewModelFactory).get(UserProfileViewModel.class);

        activityUserProfileBinding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        activityUserProfileBinding.setViewModel(userProfileViewModel);
        userProfileViewModel.updateData(1);

        userProfileViewModel.getUserDataModel()
                .observe(this, new Observer<UserDataModel>() {
                    @Override
                    public void onChanged(@Nullable UserDataModel userDataModel) {
                        Log.i(TAG, "userDataModel: " + userDataModel.getUserName() +
                                ", " + userDataModel.getUserPhone());
                        activityUserProfileBinding.setDataModel(userDataModel);
                    }
                });

        userProfileViewModel.getError().observe(this, new Observer<Throwable>() {
            @Override
            public void onChanged(@Nullable Throwable throwable) {
                Log.i(TAG, "Throwable: " + throwable);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
