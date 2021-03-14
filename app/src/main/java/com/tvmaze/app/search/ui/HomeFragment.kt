package com.tvmaze.app.search.ui

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.flexsentlabs.extensions.viewModel
import com.tvmaze.app.R
import com.tvmaze.app.app.extensions.hideKeyboard
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.autoDisposable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_home.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import timber.log.Timber


class HomeFragment : Fragment(R.layout.fragment_home), KodeinAware, SearchView.OnQueryTextListener {

    override val kodein: Kodein by kodein()
    private val viewModel: SearchViewModel by viewModel()

    private val searchTvShowAdapter = SearchTvShowAdapter()
    private val suggestionsAdapter = SuggestionsAdapter { searchView.setQuery(it.text, true) }

    private lateinit var keyboardEventListener: KeyboardEventListener

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        suggestionsRecyclerView.visibility = View.INVISIBLE
        setupKeyboardListener()
        setupRecyclerViews()
        searchView.setOnQueryTextListener(this)
    }

    override fun onResume() {
        super.onResume()

        keyboardEventListener.registerKeyboardListener()

        listenToSuggestions()
    }

    private fun listenToSuggestions() {
        viewModel.listenToSuggestions()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .autoDisposable(AndroidLifecycleScopeProvider.from(activity?.lifecycle))
            .subscribe(
                {
                    suggestionsAdapter.setItems(it)
                    suggestionsAdapter.notifyDataSetChanged()
                },
                { Timber.e(it) }
            )
    }

    override fun onQueryTextSubmit(text: String?): Boolean {
        text?.let {
            hideKeyboard()

            viewModel.saveSuggestion(text)

            searchForTvShows(it)
        }

        return false
    }

    private fun searchForTvShows(queryString: String) {
        viewModel
            .searchTvShow(queryString)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .autoDisposable(AndroidLifecycleScopeProvider.from(activity?.lifecycle))
            .subscribe(
                { tvShows ->
                    searchTvShowAdapter.setItems(tvShows)
                    searchTvShowAdapter.notifyDataSetChanged()
                },
                { Timber.e(it) }
            )
    }

    override fun onQueryTextChange(text: String?): Boolean {

        return false
    }

    private fun setupRecyclerViews() {
        requestRecyclerView.adapter = searchTvShowAdapter
        requestRecyclerView.layoutManager = LinearLayoutManager(context)

        suggestionsRecyclerView.layoutManager = LinearLayoutManager(context)
        suggestionsRecyclerView.adapter = suggestionsAdapter
    }

    override fun onPause() {
        super.onPause()

        keyboardEventListener.unregisterKeyboardListener()
    }

    private fun setupKeyboardListener() {
        keyboardEventListener = KeyboardEventListener(activity as AppCompatActivity) {
            if (suggestionsRecyclerView.visibility == View.VISIBLE) {
                suggestionsRecyclerView.visibility = View.INVISIBLE
            } else {
                suggestionsRecyclerView.visibility = View.VISIBLE
            }
        }
    }
}

