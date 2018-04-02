package com.example.dzhuchinskyi.mvvmdemo.presentation

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import com.example.dzhuchinskyi.mvvmdemo.di.DaggerTestProfileActivityComponent
import com.example.dzhuchinskyi.mvvmdemo.di.TestActivityModule
import com.example.dzhuchinskyi.mvvmdemo.domain.TestUserProfileInteractorImpl
import com.example.dzhuchinskyi.mvvmdemo.domain.UserDataModel
import io.reactivex.schedulers.Schedulers
import org.junit.Test
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.junit.Rule
import org.mockito.Mockito.*
import junit.framework.Assert.assertEquals


class UserProfileViewModelTest {
    @Mock
    private lateinit var observer: Observer<UserDataModel>

    private lateinit var liveData: MutableLiveData<UserDataModel>

    lateinit var viewModel: UserProfileViewModel

    @Mock
    private lateinit var viewModelMocked: UserProfileViewModel

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        val userInteractor = TestUserProfileInteractorImpl()

        val component = DaggerTestProfileActivityComponent
                .builder()
                .testActivityModule(TestActivityModule())
                .testUserProfileComponent(userInteractor.userProfileComponent)
                .build()
        component.inject(this)

        viewModel = UserProfileViewModel(userInteractor, Schedulers.trampoline(), Schedulers.trampoline())

        liveData = MutableLiveData<UserDataModel>()

        `when`(viewModelMocked.getUserDataModel())
                .thenReturn(liveData)
    }

    @Test
    fun testLiveDataObservable() {
        val returnData = UserDataModel(1, "Denis", "+38078759899", emptyList(), 32F)
        liveData.value = returnData
        viewModelMocked.getUserDataModel().observeForever(observer)
        viewModelMocked.updateData(1)

        verify(observer).onChanged(returnData)
    }

    @Test
    fun testLiveDataObservableInjected() {
        viewModel.getUserDataModel().observeForever(observer)
        viewModel.updateData(1)

        assertEquals(8F, viewModel.getUserDataModel().value!!.totalSaved)
        assertEquals("Denis Zhuchinski", viewModel.getUserDataModel().value!!.userName)
        assertEquals("+380936789367", viewModel.getUserDataModel().value!!.userPhone)
    }
}