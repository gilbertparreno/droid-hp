package com.droid.hp.screen.tab

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.droid.hp.R
import com.droid.hp.network.model.Job
import com.droid.hp.utils.ItemOffsetDecoration
import kotlinx.android.synthetic.main.item_job.view.*
import java.text.SimpleDateFormat


class JobsAdapter : RecyclerView.Adapter<JobsAdapter.ItemViewHolder>() {

    val rawDateFormat = SimpleDateFormat("yyyy-MM-dd")
    val monthYearFormat = SimpleDateFormat("MMMM yyyy")
    val dayFormat = SimpleDateFormat("d")

    private val jobs = mutableListOf<Job.JobsItem>()

    fun addItems(newJobs: List<Job.JobsItem>) {
        val oldSize = jobs.size
        jobs.addAll(newJobs)

        val newSize = newJobs.size
        notifyItemRangeInserted(oldSize, newSize)
    }

    fun removeItem(jobsItem: Job.JobsItem) {
        val index = jobs.indexOf(jobsItem)
        jobs.removeAt(index)
        notifyItemRemoved(index)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(parent.inflate(R.layout.item_job, false))
    }

    override fun getItemCount(): Int = jobs.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(jobs[position])
    }

    private fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Job.JobsItem) {
            itemView.lblJobName.text = item.category
            itemView.lblStatus.text = item.status

            val rawDate = rawDateFormat.parse(item.postedDate)
            val monthYear = monthYearFormat.format(rawDate)
            val day = dayFormat.format(rawDate)

            itemView.lblPosted.text = "${getDayFormat(day.toInt())} $monthYear"

            itemView.rvJobSeekers.apply {
                val spanCount = when {
                    item.connectedBusinesses == null -> 4
                    item.connectedBusinesses.size < 4 -> item.connectedBusinesses.size
                    else -> 4
                }
                layoutManager = GridLayoutManager(context, spanCount)
                addItemDecoration(ItemOffsetDecoration(context, R.dimen.mp_quarter))
                val hiredAdapter = HiredAdapter()
                this.adapter = hiredAdapter
                hiredAdapter.addItems(item.connectedBusinesses)

                item.connectedBusinesses?.let {
                    itemView.lblHiringStatus.text = resources.getQuantityString(R.plurals.lbl_hired_business, it.size, it.size)
                }
                itemView.imgMenu.setOnClickListener {
                    val popup = PopupMenu(context, it)
                    popup.gravity = Gravity.RIGHT
                    popup.setOnMenuItemClickListener {
                        removeItem(item)
                        return@setOnMenuItemClickListener true
                    }
                    popup.inflate(R.menu.menu_job)
                    popup.show()
                }
            }
        }

        private fun getDayFormat(day: Int): String {
            if (day in 10..20) {
                return "${day}th"
            }
            return when (day % 10) {
                1 -> "${day}st"
                2 -> "${day}nd"
                3 -> "${day}rd"
                else -> "${day}th"
            }
        }
    }
}