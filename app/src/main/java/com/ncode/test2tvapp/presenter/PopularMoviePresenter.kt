package com.ncode.test2tvapp.presenter

import android.view.ViewGroup
import androidx.leanback.widget.BaseCardView
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.Presenter
import com.bumptech.glide.Glide
import com.ncode.test2tvapp.model.PopularResult
import com.ncode.test2tvapp.model.Result
import com.ncode.test2tvapp.retrofit.RetroClient

class PopularMoviePresenter: Presenter() {
    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder? {
        var cardView= ImageCardView(parent!!.context)
        cardView.cardType= BaseCardView.CARD_TYPE_INFO_UNDER_WITH_EXTRA
        cardView.infoVisibility= BaseCardView.CARD_REGION_VISIBLE_ACTIVATED
        return ViewHolder(cardView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, item: Any?) {
        var popularList=item as Result
        val cardView=viewHolder!!.view as ImageCardView
        cardView.setMainImageDimensions(400,550)
        Glide.with(viewHolder.view.context).load(RetroClient.POSTER_URL+popularList.poster_path).centerCrop().into(cardView.mainImageView)
        cardView.titleText=popularList.original_title
        cardView.contentText=popularList.vote_average.toString()
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {

        }
    }