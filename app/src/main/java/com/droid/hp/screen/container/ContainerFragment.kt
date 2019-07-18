package com.droid.hp.screen.container

import android.app.Activity
import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.droid.hp.R
import com.droid.hp.app.App
import com.droid.hp.network.model.ApiResponse
import com.droid.hp.network.model.Job
import com.droid.hp.screen.base.BaseFragment
import com.droid.hp.screen.tab.TabView
import kotlinx.android.synthetic.main.fragment_container.*
import javax.inject.Inject


class ContainerFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: ContainerViewModel

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        val activity = context as Activity

        let {
            DaggerContainerComponent.builder()
                    .appComponent((activity.application as App).appComponent)
                    .containerModule(ContainerModule())
                    .build().inject(this)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_container, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()

        viewPager.adapter = TabPagerAdapter(fragmentManager!!)
        viewPager.offscreenPageLimit = TabPagerAdapter.PAGE_COUNT
        tabLayout.setupWithViewPager(viewPager)

        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory)[ContainerViewModel::class.java]
        viewModel.getLiveDataTab().observe(this,
                Observer<ApiResponse<List<Job.JobsItem>>> { response ->
                    if (response.throwable == null) {
                        response.data?.let { data ->
                            val closed = mutableListOf<Job.JobsItem>()
                            val open = mutableListOf<Job.JobsItem>()
                            data.forEach { item ->
                                if (item.status.equals("closed", true)) {
                                    closed.add(item)
                                } else {
                                    open.add(item)
                                }
                            }
                            (viewPager.adapter as TabPagerAdapter).apply {
                                (getItem(TabPagerAdapter.TAB1) as TabView).addJobs(open)
                                (getItem(TabPagerAdapter.TAB2) as TabView).addJobs(closed)
                            }
                        }
                    }
                })
        viewModel.getProjectList()
    }

    private fun initViews() {
        val root = tabLayout.getChildAt(0)
        if (root is LinearLayout) {
            root.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
            val drawable = GradientDrawable()
            drawable.setColor(ContextCompat.getColor(context!!, R.color.gray))
            drawable.setSize(2, 1)
            root.dividerPadding = 10
            root.dividerDrawable = drawable
        }
    }

    companion object {
        val TAG = "ContainerFragment"
    }
}
