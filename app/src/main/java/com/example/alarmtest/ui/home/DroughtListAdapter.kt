package com.example.alarmtest.ui.home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.alarmtest.R
import com.example.alarmtest.databinding.CardListDroughtIndexItemBinding
import com.example.alarmtest.models.Kbdi
import com.example.alarmtest.models.KbdiPreference
import java.util.*

class DroughtListAdapter :
    ListAdapter<Kbdi,
            DroughtListAdapter.ListViewHolder>(DroughtDiffCallBack()) {
    private var onItemClickCallback: OnItemClickCallback? = null

    private var context: Context? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val binding = CardListDroughtIndexItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        context = parent.context
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ListViewHolder(private val itemBinding: CardListDroughtIndexItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(kbdi: Kbdi) {
            itemBinding.apply {
                tvFieldName.text = kbdi.peatlandCover.name

                val date = kbdi.date
                tvDate.text = date.toString()

                tvIndex.text = "Indeks : ${kbdi.kbdi}"
                chip.text = kbdi.status

                btnNotif.setOnClickListener { onItemClickCallback?.onItemClicked(kbdi) }

                val kbdiPreference = context?.let { KbdiPreference(it) }

                if (kbdi.isActive) btnNotif.setImageResource(R.drawable.ic_baseline_notifications_active_24)
                else btnNotif.setImageResource(R.drawable.ic_baseline_notifications_24)

                if (kbdiPreference?.getKbdi()?.id == kbdi.id) btnNotif.setImageResource(R.drawable.ic_baseline_notifications_active_24)

                when (kbdi.status) {
                    "Low" -> chip.setChipBackgroundColorResource(R.color.lightYellow1)
                    "Moderate" -> chip.setChipBackgroundColorResource(R.color.lightYellow2)
                    "High" -> chip.setChipBackgroundColorResource(R.color.yellow)
                    "Extreme" -> chip.setChipBackgroundColorResource(R.color.darkYellow)
                }
            }
        }
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(kbdi: Kbdi)
    }
}