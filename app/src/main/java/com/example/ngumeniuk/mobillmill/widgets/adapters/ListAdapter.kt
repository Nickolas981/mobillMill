package com.example.ngumeniuk.mobillmill.widgets.adapters

import android.support.v7.widget.RecyclerView


abstract class ListAdapter<T , VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {

    val list: MutableList<T> = ArrayList()

    override fun getItemCount(): Int = list.size

    override fun getItemId(i: Int): Long = i.toLong()

    fun change(items: List<T>) {
        clearList()
        list.addAll(items)
        notifyDataSetChanged()
    }

    fun removeAt(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }

    private fun clearList() {
        list.clear()
    }
}