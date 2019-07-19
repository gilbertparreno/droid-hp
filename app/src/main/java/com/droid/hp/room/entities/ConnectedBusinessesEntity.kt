package com.droid.hp.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "connectedBusinesses", foreignKeys = [ForeignKey(
        entity = JobEntity::class,
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("jobId")
    )]
)
data class ConnectedBusinessesEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "thumbnail")
    val thumbnail: String,
    @ColumnInfo(name = "isHired")
    val isHired: Boolean,
    @ColumnInfo(name = "jobId")
    val jobId: Int
)