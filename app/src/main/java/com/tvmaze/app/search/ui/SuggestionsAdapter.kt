package com.tvmaze.app.search.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tvmaze.app.R
import com.tvmaze.app.search.db.SuggestionEntity
import com.tvmaze.app.search.domain.SuggestionItem
import kotlinx.android.synthetic.main.item_suggestion.view.*

class SuggestionsAdapter(private val clickListener: (item: SuggestionItem) -> Unit) :
    RecyclerView.Adapter<SuggestionsAdapter.ViewHolder>() {

    private var list: List<SuggestionItem> = emptyList()

    override fun getItemCount(): Int =
        list.count()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position], clickListener)
    }

    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: SuggestionItem, clickListener: (item: SuggestionItem) -> Unit) {
            itemView.suggestionTextView.text = item.text
            itemView.layout.setOnClickListener { clickListener.invoke(item) }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val view = inflater.inflate(R.layout.item_suggestion, parent, false)
                return ViewHolder(view)
            }
        }
    }

    fun setItems(newList: List<SuggestionItem>) {
        list = newList
    }
}