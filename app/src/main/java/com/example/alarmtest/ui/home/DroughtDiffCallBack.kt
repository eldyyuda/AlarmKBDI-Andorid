package com.example.alarmtest.ui.home

import androidx.recyclerview.widget.DiffUtil
import com.example.alarmtest.models.Kbdi

class DroughtDiffCallBack: DiffUtil.ItemCallback<Kbdi>() {
    override fun areItemsTheSame(oldItem: Kbdi, newItem: Kbdi): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Kbdi, newItem: Kbdi): Boolean =
        oldItem == newItem
}