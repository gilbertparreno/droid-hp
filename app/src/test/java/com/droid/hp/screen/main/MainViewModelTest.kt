package com.droid.hp.screen.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.droid.hp.network.model.ApiResponse
import com.droid.hp.network.model.Job
import com.droid.hp.network.repository.JobRepositoryInteractor
import com.droid.hp.network.repository.JobRepositoryInteractorImpl
import com.droid.hp.screen.container.ContainerViewModel
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations


@RunWith(JUnit4::class)
class MainViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var jobRepositoryInteractor: JobRepositoryInteractor

    lateinit var mainViewModel: ContainerViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        jobRepositoryInteractor = JobRepositoryInteractorImpl()
        this.mainViewModel = ContainerViewModel(this.jobRepositoryInteractor)

        RxJavaPlugins.setIoSchedulerHandler { scheduler -> Schedulers.trampoline() }
        RxAndroidPlugins.setMainThreadSchedulerHandler { scheduler -> Schedulers.trampoline() }
    }

    @Test
    fun getProjectListTestSuccess() {
        val observer = mock(Observer::class.java) as Observer<ApiResponse<List<Job.JobsItem>>>
        this.mainViewModel.getLiveDataTab().observeForever(observer)

        this.mainViewModel.getProjectList()

        assertNotNull(this.mainViewModel.getLiveDataTab().value)
        assertNotNull(this.mainViewModel.getLiveDataTab().value?.data)
        assertNull(this.mainViewModel.getLiveDataTab().value?.throwable)
    }

    @Test
    fun getProjectListTestError() {
//        val observer = mock(Observer::class.java) as Observer<ApiResponse<List<Job.JobsItem>>>
//        this.mainViewModel.getLiveDataTab().observeForever(observer)
//
//        this.mainViewModel.getProjectList()
//
//        assertNotNull(this.mainViewModel.getLiveDataTab().value)
//        assertNull(this.mainViewModel.getLiveDataTab().value?.data)
//        assertNotNull(this.mainViewModel.getLiveDataTab().value?.throwable)
    }
}