package com.ncode.test2tvapp.view.fragment

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.leanback.app.DetailsSupportFragment
import androidx.leanback.app.DetailsSupportFragmentBackgroundController
import androidx.leanback.widget.*
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.ncode.test2tvapp.R
import com.ncode.test2tvapp.model.Result
import com.ncode.test2tvapp.presenter.DetailsDescriptionPresenter
import com.ncode.test2tvapp.retrofit.RetroClient
import com.ncode.test2tvapp.view.activity.PlayerActivity

class DetailsFragment():DetailsSupportFragment()
{
    private val TAG="DetailsFragment"
    private lateinit var classPresenterSelector: ClassPresenterSelector
    private lateinit var madapter:ArrayObjectAdapter
    private lateinit var backgroundController: DetailsSupportFragmentBackgroundController
    private lateinit var  result:Result

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        result=requireActivity().intent.getSerializableExtra("Movie") as Result
        Log.d(TAG, "onActivityCreated:"+result.title)
        classPresenterSelector= ClassPresenterSelector()
        backgroundController= DetailsSupportFragmentBackgroundController(this)
        madapter= ArrayObjectAdapter(classPresenterSelector)
        detailsRowPresenter()
        setUpDetailsOverViewRow()
        adapter=madapter
        setDetailsOverViewBackgroundImage()
    }

   private fun setDetailsOverViewBackgroundImage()
    {
        backgroundController.enableParallax()

        Glide.with(requireActivity()).asBitmap().load(RetroClient.POSTER_URL+result.poster_path).centerCrop().into<SimpleTarget<Bitmap>>(object :SimpleTarget<Bitmap>(){
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                backgroundController.coverBitmap=resource
                madapter.notifyArrayItemRangeChanged(0,madapter.size())
            }
        })
    }

    private fun setUpDetailsOverViewRow()
    {
        val row=DetailsOverviewRow(result)
        val width = convertDpToPixel(requireActivity(),350)
        val height = convertDpToPixel(requireActivity(), 550)
        Glide.with(requireActivity()).load(RetroClient.POSTER_URL+result.poster_path).centerCrop().into<SimpleTarget<Drawable>>(object :SimpleTarget<Drawable>(width,height){
            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                Log.d(TAG, "onResourceReady: ")
                row.imageDrawable=resource
                madapter.notifyArrayItemRangeChanged(0,madapter.size())
            }
        })

        val arrayAction=ArrayObjectAdapter()
        arrayAction.add(Action(1L,"Watch Trailer",))
       row.actionsAdapter=arrayAction
        madapter.add(row)
    }

    private  fun detailsRowPresenter()
    {
            val detailsPresenter=FullWidthDetailsOverviewRowPresenter(DetailsDescriptionPresenter())

            detailsPresenter.onActionClickedListener=object :OnActionClickedListener{
                override fun onActionClicked(action: Action?) {
                    if(action!!.id==1L)
                    {
                        val intent=Intent(requireContext(),PlayerActivity::class.java)
                        startActivity(intent)
                        Toast.makeText(requireContext(), "OKK", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            classPresenterSelector.addClassPresenter(DetailsOverviewRow::class.java,detailsPresenter)

    }

    private fun convertDpToPixel(context: Context, dp: Int): Int {

        val density = context.applicationContext.resources.displayMetrics.density

        return Math.round(dp.toFloat() * density)

    }


}