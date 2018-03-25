package com.example.ngumeniuk.mobillmill.widgets.adapters

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import com.example.ngumeniuk.curogram.utils.Differeble
import com.example.ngumeniuk.curogram.utils.NoteDiffUtilCallback
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg


abstract class ListAdapter<T : Differeble, VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {

    val list: MutableList<T> = ArrayList()

    override fun getItemCount(): Int = list.size

    override fun getItemId(i: Int): Long = i.toLong()

    fun change(items: List<T>) {
        async(UI) {
            val diff = bg { DiffUtil.calculateDiff(NoteDiffUtilCallback(list, items.toList())) }.await()
            dispatchUpdates(diff, items)
        }
    }

    private fun dispatchUpdates(diff: DiffUtil.DiffResult, items: List<T>) {
        clearList()
        list.addAll(items)
        diff.dispatchUpdatesTo(this)

    }

    fun removeAt(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }

    private fun clearList() {
        list.clear()
    }
}