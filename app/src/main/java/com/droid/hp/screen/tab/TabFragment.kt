package com.droid.hp.screen.tab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.droid.hp.R
import com.droid.hp.network.model.Job
import com.droid.hp.screen.base.BaseFragment
import com.droid.hp.screen.container.TabPagerListener
import com.droid.hp.utils.ItemOffsetDecoration
import kotlinx.android.synthetic.main.fragment_tab.*

class TabFragment(private val tabPagerListener: TabPagerListener?) : BaseFragment(), TabView {

    private val adapter = JobsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        retainInstance = true
        return inflater.inflate(R.layout.fragment_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        srJobs.setOnRefreshListener {
            tabPagerListener?.onSwipeRefresh()
        }

        rvJobs.adapter = adapter
        rvJobs.addItemDecoration(ItemOffsetDecoration(context!!, R.dimen.mp_quarter))
        rvJobs.layoutManager = LinearLayoutManager(context!!)
    }

    override fun addJobs(jobs: List<Job.JobsItem>) {
        srJobs.isRefreshing = false
        adapter.addItems(jobs)
    }

    override fun onError() {
        srJobs.isRefreshing = false
    }
}