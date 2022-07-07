package com.ncode.test2tvapp.view.fragment

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.*
import com.ncode.test2tvapp.controller.PopularMoviesController
import com.ncode.test2tvapp.controller.TopRatedMoviesController
import com.ncode.test2tvapp.controller.TrendingMoviesController
import com.ncode.test2tvapp.controller.UpcomingMoviesController
import com.ncode.test2tvapp.model.*
import com.ncode.test2tvapp.presenter.PopularMoviePresenter
import com.ncode.test2tvapp.presenter.TopRatedMoviePresenter
import com.ncode.test2tvapp.presenter.TrendingMoviePresenter
import com.ncode.test2tvapp.presenter.UpcomingMoviePresenter
import com.ncode.test2tvapp.retrofit.ControllerInterFace
import com.ncode.test2tvapp.retrofit.RetroClient
import com.ncode.test2tvapp.util.Config
import com.ncode.test2tvapp.view.activity.DetailsActivity

class MainFragment() : BrowseSupportFragment(), ControllerInterFace {
  private lateinit var trendMovieList:List<Result>
  private lateinit var topRatedMovieList:List<Result>
  private lateinit var upComingMovieList:List<Result>
  private lateinit var popularMovieList:List<Result>
  private val TAG="MAIN FRAGMENT"
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        trendMovieList=ArrayList()
        topRatedMovieList=ArrayList()
        upComingMovieList=ArrayList()
        popularMovieList=ArrayList()
        apiCall()

    }

    private fun setUiElements() {
        title = "My App"
        headersState = BrowseSupportFragment.HEADERS_ENABLED
        brandColor=Color.CYAN
    }
    private fun loadRows()
    {
        val headerItem=HeaderItem(0,Config.TRENDING)
        val headerItem1=HeaderItem(1,Config.TOP_RATED)
        val headerItem2=HeaderItem(2,Config.POPULAR)
        val headerItem3=HeaderItem(3,Config.UPCOMING)
        val rowsAdapter=ArrayObjectAdapter(ListRowPresenter())

        val arrayObjectAdapter=ArrayObjectAdapter(TrendingMoviePresenter())
        val popularObjectAdapter=ArrayObjectAdapter(PopularMoviePresenter())
        val upcomingObjectAdapter=ArrayObjectAdapter(UpcomingMoviePresenter())
        val topRatedObjectAdapter=ArrayObjectAdapter(TopRatedMoviePresenter())

        for(element in trendMovieList)
        {

            arrayObjectAdapter.add(element)
        }
        for (p in popularMovieList)
        {
            popularObjectAdapter.add(p)
        }
        for (u in upComingMovieList)
        {
            upcomingObjectAdapter.add(u)
        }
        for (t in topRatedMovieList)
        {
            topRatedObjectAdapter.add(t)
        }
        rowsAdapter.add(ListRow(headerItem,arrayObjectAdapter))
        rowsAdapter.add(ListRow(headerItem1,topRatedObjectAdapter))
        rowsAdapter.add(ListRow(headerItem2,popularObjectAdapter))
        rowsAdapter.add(ListRow(headerItem3,upcomingObjectAdapter))

        adapter=rowsAdapter
        onItemViewClickedListener=object:OnItemViewClickedListener{
            override fun onItemClicked(itemViewHolder: Presenter.ViewHolder?, item: Any, rowViewHolder: RowPresenter.ViewHolder?, row: Row?)
            {
                if (item is Result)
                {

                    val intent = Intent(activity!!, DetailsActivity::class.java)
                    intent.putExtra("Movie",item)
                    startActivity(intent)
                }
            }
        }
    }

    private fun apiCall()
    {
        TrendingMoviesController(requireContext(),this).callApi()
        /*TopRatedMoviesController(requireContext(),this).callTopRatedApi()
        UpcomingMoviesController(requireContext(),this).callUpcomingApi()
        PopularMoviesController(requireContext(),this).callPopularApi()
*/
    }

    override fun <T> onSuccess(response: T,method:String) {
        when(method)
        {
            Config.TRENDING->{
                val res=response as TrendingMoviesResponse
                trendMovieList=res.results
                TopRatedMoviesController(requireContext(),this).callTopRatedApi()
                Log.d(TAG, "onSuccess:1")

            }
            Config.TOP_RATED->{
                val res=response as TrendingMoviesResponse
                topRatedMovieList=res.results
                UpcomingMoviesController(requireContext(),this).callUpcomingApi()
                Log.d(TAG, "onSuccess: 2")
            }
            Config.UPCOMING->{
                val res=response as TrendingMoviesResponse
                upComingMovieList=res.results
                PopularMoviesController(requireContext(),this).callPopularApi()
                Log.d(TAG, "onSuccess: 3")
                Log.d("", "onBindViewHolder: "+ RetroClient.BACKDROP_URL + upComingMovieList[0].backdrop_path)
            }
            Config.POPULAR->{
                val res=response as TrendingMoviesResponse
                popularMovieList=res.results
                Log.d(TAG, "onSuccess: 4")
                setUiElements()
                loadRows()
            }

        }

    }
    override fun onFail(message: String?) {

    }
}