package com.droid.hp.screen.container

import com.droid.hp.di.ActivityScope
import com.droid.hp.network.repository.JobRepositoryInteractor
import com.droid.hp.network.repository.JobRepositoryInteractorImpl
import com.droid.hp.network.service.JobService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ContainerModule {

    @Provides
    @ActivityScope
    fun providesProjectRepository(retrofit: Retrofit): JobRepositoryInteractor {
        return JobRepositoryInteractorImpl(retrofit.create(JobService::class.java))
    }
}