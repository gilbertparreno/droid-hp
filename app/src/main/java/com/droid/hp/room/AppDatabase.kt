package com.droid.hp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.droid.hp.room.dao.JobDao
import com.droid.hp.room.entities.ConnectedBusinessesEntity
import com.droid.hp.room.entities.JobEntity

@Database(entities = [JobEntity::class, ConnectedBusinessesEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun jobDao(): JobDao
}