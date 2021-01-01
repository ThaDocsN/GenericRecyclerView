package com.example.android.genericrecyclerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter: ListAdapter<Any,BaseAdapter.ViewHolder>(ItemDiffCallback()) {
    abstract fun getLayoutIdForType(viewType: Int): Int

    class ViewHolder(private val binding: ViewDataBinding):RecyclerView.ViewHolder(binding.root) {

        fun bind(item:Any){
            binding.setVariable(BR.item, item)
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater                = LayoutInflater.from(parent.context)
        val binding:ViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutIdForType(viewType),parent, false)
        return ViewHolder(binding)    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ItemDiffCallback:DiffUtil.ItemCallback<Any>(){
    override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
        return oldItem == newItem
    }

}