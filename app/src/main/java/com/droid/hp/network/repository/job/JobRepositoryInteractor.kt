package com.droid.hp.network.repository.job

import com.droid.hp.network.model.Job
import com.droid.hp.network.model.Project
import com.droid.hp.room.entities.JobConnectedBusinesses
import com.droid.hp.room.entities.JobEntity
import io.reactivex.Completable
import io.reactivex.Single

interface JobRepositoryInteractor {
    fun getJobs() : Single<Job>

    fun saveLocalJobs(jobItems : List<Job.JobsItem>) : Completable

    fun getLocalJobs() : Single<List<JobConnectedBusinesses>>
}