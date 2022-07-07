package com.ncode.test2tvapp.controller

import android.content.Context
import android.util.Log
import com.ncode.test2tvapp.model.TrendingMoviesResponse
import com.ncode.test2tvapp.retrofit.ControllerInterFace
import com.ncode.test2tvapp.retrofit.RetroClient
import com.ncode.test2tvapp.util.Config
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TopRatedMoviesController(var context: Context, var controllerInterFace: ControllerInterFace) {

    fun callTopRatedApi()
    {
        var call: Call<TrendingMoviesResponse> = RetroClient.getInstance().getTopRatedMovies()
        call.enqueue(object : Callback<TrendingMoviesResponse> {
            override fun onResponse(
                call: Call<TrendingMoviesResponse>,
                response: Response<TrendingMoviesResponse>
            ) {
                Log.d("", "onResponse:==>")
                if(response.body()!=null)
                {
                    controllerInterFace.onSuccess(response.body(), Config.TOP_RATED)
                }
            }

            override fun onFailure(call: Call<TrendingMoviesResponse>, t: Throwable) {
                controllerInterFace.onFail(t.message)
            }
        })
    }
}