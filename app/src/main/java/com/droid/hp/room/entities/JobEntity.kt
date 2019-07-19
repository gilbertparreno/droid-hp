package com.droid.hp.room.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jobs")
data class JobEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "category")
    val category: String?,
    @ColumnInfo(name = "postedDate")
    val postedDate: String?,
    @ColumnInfo(name = "status")
    val status: String?,
    @ColumnInfo(name = "detailsLink")
    val detailsLink: String?,
    @ColumnInfo(name = "jobId")
    val jobId: Int
)