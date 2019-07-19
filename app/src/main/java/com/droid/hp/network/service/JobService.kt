package com.droid.hp.network.service

import com.droid.hp.network.model.Job
import com.droid.hp.network.model.Project
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path


interface JobService {

    @GET("hipgrp-assets/tech-test/jobs.json")
    fun getProjectList(): Single<Job>
}