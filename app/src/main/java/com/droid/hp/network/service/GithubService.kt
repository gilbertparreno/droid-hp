package com.droid.hp.network.service

import com.droid.hp.network.model.Project
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path


interface GithubService {

    @GET("users/{user}/repos")
    fun getProjectList(@Path("user") user: String): Single<List<Project>>
}