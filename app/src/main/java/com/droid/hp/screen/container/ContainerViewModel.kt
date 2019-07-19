package com.droid.hp.screen.container

import androidx.lifecycle.*
import com.droid.hp.network.model.ApiResponse
import com.droid.hp.network.model.Job
import com.droid.hp.network.repository.job.JobRepositoryInteractor
import com.droid.hp.room.entities.JobEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.concurrent.atomic.AtomicReference
import javax.inject.Inject

class ContainerViewModel @Inject constructor(private val jobRepository: JobRepositoryInteractor) :
        ViewModel(),
        LifecycleObserver {

    private val mutableLiveTab = MutableLiveData<ApiResponse<List<Job.JobsItem>>>()

    private val disposable = CompositeDisposable()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        getProjectList()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        disposable.clear()
    }

    fun getLiveDataTab(): LiveData<ApiResponse<List<Job.JobsItem>>> {
        return mutableLiveTab
    }

    fun getProjectList(hasInternet: Boolean = true) {
        if (hasInternet) getJobsFromNetwork()
        else getJobsFromLocal()
    }

    private fun getJobsFromNetwork() {
        val atomicReference = AtomicReference<Job>()
        disposable.add(
                jobRepository.getJobs()
                        .flatMapCompletable { data ->
                            atomicReference.set(data)
                            jobRepository.saveLocalJobs(data.jobs)
                        }.subscribe({
                            mutableLiveTab.postValue(ApiResponse(atomicReference.get().jobs))
                        }, { throwable ->
                            mutableLiveTab.postValue(ApiResponse(throwable = throwable))
                        }
                        ))
    }

    private fun getJobsFromLocal() {
        disposable.add(
                jobRepository.getLocalJobs().subscribe({ local ->
                    val data = local.map { jobConnectedBusiness ->
                        Job.JobsItem(
                                jobId = jobConnectedBusiness.jobs.jobId,
                                connectedBusinesses = jobConnectedBusiness.connectedBusinesses.map { businessEntity ->
                                    Job.JobsItem.ConnectedBusinessesItem(
                                            thumbnail = businessEntity.thumbnail,
                                            isHired = businessEntity.isHired,
                                            businessId = businessEntity.id
                                    )
                                },
                                detailsLink = jobConnectedBusiness.jobs.detailsLink ?: "",
                                category = jobConnectedBusiness.jobs.category ?: "",
                                postedDate = jobConnectedBusiness.jobs.postedDate ?: "",
                                status = jobConnectedBusiness.jobs.status ?: ""
                        )
                    }
                    if (data.isNotEmpty()) {
                        mutableLiveTab.postValue(ApiResponse(data))
                    } else {
                        mutableLiveTab.postValue(ApiResponse(throwable = Throwable("No data in local.")))
                    }
                }, { throwable ->
                    mutableLiveTab.postValue(ApiResponse(throwable = throwable))
                })
        )
    }
}
