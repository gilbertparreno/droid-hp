package com.droid.hp.network.model

data class ApiResponse<T> (val data : T? = null, val throwable: Throwable? = null)