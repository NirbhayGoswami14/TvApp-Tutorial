package com.ncode.test2tvapp.presenter

import androidx.leanback.widget.AbstractDetailsDescriptionPresenter
import com.ncode.test2tvapp.model.Result

class DetailsDescriptionPresenter():AbstractDetailsDescriptionPresenter() {
    override fun onBindDescription(vh: ViewHolder?, item: Any?) {
           val res= item as Result
                vh!!.title.text=res.title
                vh.subtitle.text=res.media_type
                vh.body.text=res.overview
            }
}