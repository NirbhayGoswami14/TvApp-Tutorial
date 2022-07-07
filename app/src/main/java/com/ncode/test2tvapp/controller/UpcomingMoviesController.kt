package com.ncode.test2tvapp.controller

import android.content.Context
import android.util.Log
import com.ncode.test2tvapp.model.MoviesResponse
import com.ncode.test2tvapp.retrofit.ControllerInterFace
import com.ncode.test2tvapp.retrofit.RetroClient
import com.ncode.test2tvapp.util.Config
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpcomingMoviesController(var context: Context, var controllerInterFace: ControllerInterFace) {

    fun callUpcomingApi()
    {
        val call: Call<MoviesResponse> = RetroClient.getInstance().getUpcomingMovies()
        call.enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                Log.d("", "onResponse:==>")
                if(response.body()!=null)
                {
                    controllerInterFace.onSuccess(response.body(), Config.UPCOMING)
                }
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                controllerInterFace.onFail(t.message)
            }
        })
    }
}