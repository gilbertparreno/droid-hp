package com.droid.hp.screen.container

import androidx.lifecycle.*
import com.droid.hp.network.model.ApiResponse
import com.droid.hp.network.model.Job
import com.droid.hp.network.repository.JobRepositoryInteractor
import io.reactivex.disposables.CompositeDisposable
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

    fun getProjectList() {
        disposable.add(jobRepository.getProjectList()
                .subscribe(
                        { data ->
                            mutableLiveTab.postValue(ApiResponse(data))
                        }, { throwable ->
                    mutableLiveTab.postValue(ApiResponse(throwable = throwable))
                }
                ))
    }
}
