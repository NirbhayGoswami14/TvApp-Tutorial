package com.ncode.test2tvapp.presenter

import android.view.ViewGroup
import androidx.leanback.widget.BaseCardView
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.Presenter
import com.bumptech.glide.Glide
import com.ncode.test2tvapp.model.Result
import com.ncode.test2tvapp.retrofit.RetroClient

class TrendingMoviePresenter():Presenter() {
    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder? {
        var cardView=ImageCardView(parent!!.context)
        cardView.cardType=BaseCardView.CARD_TYPE_INFO_UNDER_WITH_EXTRA
        cardView.infoVisibility=BaseCardView.CARD_REGION_VISIBLE_ACTIVATED
        return ViewHolder(cardView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, item: Any?) {
        var trendingList=item as Result
        val cardView=viewHolder!!.view as ImageCardView
        cardView.setMainImageDimensions(400,550)
        Glide.with(viewHolder.view.context).load(RetroClient.POSTER_URL+trendingList.poster_path).centerCrop().into(cardView.mainImageView)
        cardView.titleText=trendingList.original_title
        cardView.contentText=trendingList.vote_average.toFloat().toString()
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {

    }
}