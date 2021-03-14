package com.tvmaze.app.search.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tvmaze.app.R
import com.tvmaze.app.tvshow.domain.TvShowItem
import com.tvmaze.app.tvshow.domain.TvShowListDiffUtil
import kotlinx.android.synthetic.main.tv_show_item.view.*

class SearchTvShowAdapter : RecyclerView.Adapter<SearchTvShowAdapter.ViewHolder>() {

    private var list: List<TvShowItem> = emptyList()

    override fun getItemCount(): Int =
        list.count()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: TvShowItem) {
            itemView.showNameTextView.text = item.showName
            setOnClickListener(item)

            Glide
                .with(itemView)
                .load(item.showImageUrl)
                .into(itemView.showImageView)
        }

        private fun setOnClickListener(item: TvShowItem) {
            itemView.setOnClickListener {
                Navigation
                    .findNavController(itemView)
                    .navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(item))
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val view = inflater.inflate(R.layout.tv_show_item, parent, false)
                return ViewHolder(view)
            }
        }
    }

    fun setItems(tvShows: List<TvShowItem>) {
        val diffUtil = TvShowListDiffUtil(list, tvShows)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        list = tvShows
        diffResult.dispatchUpdatesTo(this)
    }
}