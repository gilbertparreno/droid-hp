package com.droid.hp.screen.container

import com.droid.hp.di.ActivityScope
import com.droid.hp.network.repository.job.JobRepositoryInteractor
import com.droid.hp.network.repository.job.JobRepositoryInteractorImpl
import com.droid.hp.network.service.JobService
import com.droid.hp.room.AppDatabase
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ContainerModule {

    @Provides
    @ActivityScope
    fun providesProjectRepository(retrofit: Retrofit, appDatabase: AppDatabase): JobRepositoryInteractor {
        return JobRepositoryInteractorImpl(retrofit.create(JobService::class.java), appDatabase)
    }
}