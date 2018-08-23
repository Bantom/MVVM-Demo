package com.example.dzhuchinskyi.mvvmdemo.presentation

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import com.example.dzhuchinskyi.mvvmdemo.di.*
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
import org.junit.After
import org.koin.java.standalone.KoinJavaStarter.startKoin
import org.koin.standalone.StandAloneContext.stopKoin
import org.koin.test.KoinTest


class UserProfileViewModelTest : KoinTest {
    @Mock
    private lateinit var observer: Observer<UserDataModel>

    @Mock
    private lateinit var viewModelMocked: UserProfileViewModel

    private lateinit var liveData: MutableLiveData<UserDataModel>

    private lateinit var viewModel: UserProfileViewModel


    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        startKoin(listOf(appModule, activityModule, userProfileModule))

        val userInteractor = TestUserProfileInteractorImpl()

        viewModel = UserProfileViewModel(userInteractor, Schedulers.trampoline(), Schedulers.trampoline())

        liveData = MutableLiveData()

        `when`(viewModelMocked.getUserDataModel())
                .thenReturn(liveData)
    }

    @After
    fun after() {
        stopKoin()
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