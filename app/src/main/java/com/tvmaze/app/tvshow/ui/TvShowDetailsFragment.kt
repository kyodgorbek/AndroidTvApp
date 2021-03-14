package com.tvmaze.app.tvshow.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.flexsentlabs.extensions.viewModel
import com.tvmaze.app.R
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_detail.showImageView
import kotlinx.android.synthetic.main.tv_show_item.showNameTextView
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein

class TvShowDetailsFragment : Fragment(R.layout.fragment_detail), KodeinAware {

    override val kodein: Kodein by kodein()
    private val viewModelTvShow: TvShowDetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val item = arguments?.let { TvShowDetailsFragmentArgs.fromBundle(it) }?.item
        viewModelTvShow.item = item
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()

        context?.let {
            Glide
                .with(it)
                .load(viewModelTvShow.item?.showImageUrl)
                .into(showImageView)
        }
    }

    private fun setupViews() {
        showNameTextView.text = viewModelTvShow.item?.showName
        showRatingTextView.text = viewModelTvShow.item?.averageRating.toString()
        showCountryTextView.text = viewModelTvShow.item?.showCountry
        genresTextView.text = viewModelTvShow.item?.genres.toString()
            .subSequence(1, viewModelTvShow.item?.genres.toString().length - 1)
    }

}