package com.droid.hp.screen.container

import com.droid.hp.di.ActivityScope
import com.droid.hp.di.AppComponent
import com.droid.hp.viewModel.ViewModelFactoryModule
import com.droid.hp.viewModel.ContainerViewModelModule
import dagger.Component

@ActivityScope
@Component(
    modules = [ContainerModule::class, ViewModelFactoryModule::class, ContainerViewModelModule::class],
    dependencies = [AppComponent::class]
)
interface ContainerComponent {
    fun inject(containerFragment: ContainerFragment)
}