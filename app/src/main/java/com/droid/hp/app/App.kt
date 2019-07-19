package com.droid.hp.app

import android.app.Application
import com.droid.hp.BuildConfig
import com.droid.hp.di.AppComponent
import com.droid.hp.di.AppModule
import com.droid.hp.di.DaggerAppComponent
import com.droid.hp.di.NetworkModule
import timber.log.Timber


class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        appComponent = DaggerAppComponent.builder()
            .networkModule(NetworkModule(applicationContext,"https://s3-ap-southeast-2.amazonaws.com/", BuildConfig.DEBUG))
            .appModule(AppModule(this))
            .build()
    }
}