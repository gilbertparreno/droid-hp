package com.droid.hp.screen.tab

import com.droid.hp.network.model.Job

interface TabView {
    fun addJobs(jobs: List<Job.JobsItem>)
    fun onError()
}