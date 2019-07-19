package com.droid.hp.di

import android.app.Application
import com.droid.hp.app.App
import com.droid.hp.room.AppDatabase
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, AppModule::class])
interface AppComponent {
    fun inject(app: App)
    fun application(): Application
    fun retrofit(): Retrofit
    fun appDatabase(): AppDatabase
}