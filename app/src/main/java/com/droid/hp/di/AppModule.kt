package com.droid.hp.di

import android.app.Application
import androidx.room.Room
import com.droid.hp.room.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val application: Application) {

    @Singleton
    @Provides
    fun providesApplication(): Application {
        return application
    }

    @Singleton
    @Provides
    fun providesRoomDatabase(): AppDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            AppDatabase::class.java, "job-db"
        ).build()
    }
}