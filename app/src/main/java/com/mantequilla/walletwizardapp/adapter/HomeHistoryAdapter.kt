package com.mantequilla.walletwizardapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mantequilla.walletwizardapp.databinding.ItemRecentActivityBinding
import com.mantequilla.walletwizardapp.models.HistoryTransactionModelElement

class HomeHistoryAdapter : RecyclerView.Adapter<HomeHistoryAdapter.MyViewHolder>() {
    inner class MyViewHolder(val binding : ItemRecentActivityBinding) : RecyclerView.ViewHolder(binding.root)
    private val diffcallback = object : DiffUtil.ItemCallback<HistoryTransactionModelElement>() {
        override fun areItemsTheSame(
            oldItem: HistoryTransactionModelElement,
            newItem: HistoryTransactionModelElement
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: HistoryTransactionModelElement,
            newItem: HistoryTransactionModelElement
        ): Boolean {
            return newItem == oldItem
        }

    }

    private val differ = AsyncListDiffer(this, diffcallback)
    var historyData : List<HistoryTransactionModelElement>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       return MyViewHolder(ItemRecentActivityBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = historyData.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = historyData[position]
        holder.binding.apply {
            tvTransactionTitle.text = data.title
            tvTransactionNominal.text = data.nominal.toString()
            tvTransactionDate.text = data.date
        }
    }
}