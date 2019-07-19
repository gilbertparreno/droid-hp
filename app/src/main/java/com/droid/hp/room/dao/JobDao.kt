package com.droid.hp.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.droid.hp.room.entities.ConnectedBusinessesEntity
import com.droid.hp.room.entities.JobConnectedBusinesses
import com.droid.hp.room.entities.JobEntity

@Dao
interface JobDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertJobs(entities: List<JobEntity>)

    @Query("SELECT * FROM jobs")
    fun getAllJobs(): List<JobConnectedBusinesses>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertConnectedBusinesses(entities: List<ConnectedBusinessesEntity>)
}