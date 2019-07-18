package com.droid.hp.screen.container

import com.droid.hp.di.ActivityScope
import com.droid.hp.network.repository.JobRepositoryInteractor
import com.droid.hp.network.repository.JobRepositoryInteractorImpl
import dagger.Module
import dagger.Provides

@Module
class ContainerModule {

    @Provides
    @ActivityScope
    fun providesProjectRepository(): JobRepositoryInteractor {
        return JobRepositoryInteractorImpl()
    }
}