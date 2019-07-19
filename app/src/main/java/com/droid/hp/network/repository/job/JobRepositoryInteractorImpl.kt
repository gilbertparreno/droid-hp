package com.droid.hp.network.repository.job

import com.droid.hp.network.model.Job
import com.droid.hp.network.service.JobService
import com.droid.hp.room.AppDatabase
import com.droid.hp.room.dao.JobDao
import com.droid.hp.room.entities.ConnectedBusinessesEntity
import com.droid.hp.room.entities.JobConnectedBusinesses
import com.droid.hp.room.entities.JobEntity
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.operators.single.SingleCreate
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class JobRepositoryInteractorImpl @Inject constructor(val jobService: JobService, val jobDao: JobDao) :
    JobRepositoryInteractor {

    override fun getJobs(): Single<Job> {
        return jobService.getProjectList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun saveLocalJobs(jobItems: List<Job.JobsItem>): Completable {
        return Completable.create {
            emitter ->
            val jobEntities = mutableListOf<JobEntity>()
            val connectedBusinesses = mutableListOf<ConnectedBusinessesEntity>()
            jobItems.forEach { jobItem ->
                jobEntities.add(
                    JobEntity(
                        jobItem.jobId,
                        jobItem.category,
                        jobItem.postedDate,
                        jobItem.status,
                        jobItem.detailsLink,
                        jobItem.jobId
                    )
                )
                jobItem.connectedBusinesses?.let {
                    it.forEach { connected ->
                        connectedBusinesses.add(ConnectedBusinessesEntity(
                            connected.businessId,
                            connected.thumbnail,
                            connected.isHired,
                            jobItem.jobId
                        ))
                    }
                }
            }

            jobDao.insertJobs(jobEntities)
            jobDao.insertConnectedBusinesses(connectedBusinesses)

            emitter.onComplete()
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getLocalJobs(): Single<List<JobConnectedBusinesses>> {
        return SingleCreate<List<JobConnectedBusinesses>> { emitter ->
            emitter.onSuccess(jobDao.getAllJobs())
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}