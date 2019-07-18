package com.droid.hp.screen.tab

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.droid.hp.R
import com.droid.hp.network.model.Job
import com.droid.hp.utils.CircleTransform
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_job_seeker.view.*

class HiredAdapter : RecyclerView.Adapter<HiredAdapter.ItemViewHolder>() {

    private val connectedBusinesses = mutableListOf<Job.JobsItem.ConnectedBusinessesItem>()

    fun addItems(newConnectedBusinesses: List<Job.JobsItem.ConnectedBusinessesItem>?) {
        newConnectedBusinesses?.let {
            val oldSize = connectedBusinesses.size
            connectedBusinesses.addAll(newConnectedBusinesses)

            val newSize = newConnectedBusinesses.size
            notifyItemRangeInserted(oldSize, newSize)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(parent.inflate(R.layout.item_job_seeker, false))
    }

    override fun getItemCount(): Int = connectedBusinesses.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(connectedBusinesses[position])
    }

    private fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Job.JobsItem.ConnectedBusinessesItem) {
            Picasso.get().load(item.thumbnail).transform(CircleTransform()).into(itemView.imgJobSeeker)
            itemView.lblHired.visibility = if (item.isHired) View.VISIBLE else View.INVISIBLE
        }
    }
}