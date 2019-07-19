package com.droid.hp.network.repository

import com.droid.hp.network.model.Job
import com.droid.hp.network.service.JobService
import com.google.gson.Gson
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class JobRepositoryInteractorImpl @Inject constructor(val jobService: JobService) :
    JobRepositoryInteractor {

    override fun getProjectList(): Single<Job> {
        return jobService.getProjectList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}