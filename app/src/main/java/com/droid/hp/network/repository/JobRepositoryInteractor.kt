package com.droid.hp.network.repository

import com.droid.hp.network.model.Job
import com.droid.hp.network.model.Project
import io.reactivex.Single

interface JobRepositoryInteractor {
    fun getProjectList() : Single<Job>
}