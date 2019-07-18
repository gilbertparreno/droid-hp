package com.droid.hp.viewModel

import androidx.lifecycle.ViewModel
import com.droid.hp.screen.container.ContainerViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ContainerViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ContainerViewModel::class)
    abstract fun containerViewModel(containerViewModel: ContainerViewModel): ViewModel
}