package com.tvmaze.app.tvshow.domain

import androidx.recyclerview.widget.DiffUtil

class TvShowListDiffUtil(val oldList: List<TvShowItem>, val newList: List<TvShowItem>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int =
        oldList.size

    override fun getNewListSize(): Int =
        newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].showName == newList[newItemPosition].showName
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}