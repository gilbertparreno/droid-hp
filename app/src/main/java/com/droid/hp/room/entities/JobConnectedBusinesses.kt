package com.droid.hp.room.entities

import androidx.room.Embedded
import androidx.room.Relation

data class JobConnectedBusinesses(
    @Embedded val jobs: JobEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "jobId",
        entity = ConnectedBusinessesEntity::class
    )
    val connectedBusinesses: List<ConnectedBusinessesEntity>
)