package com.ncode.test2tvapp.retrofit

import com.ncode.test2tvapp.R
import com.ncode.test2tvapp.model.PopularMovieResponse
import com.ncode.test2tvapp.model.TopRatedMoviesResponse
import com.ncode.test2tvapp.model.TrendingMoviesResponse
import com.ncode.test2tvapp.model.UpcomingMoviesResponse
import com.ncode.test2tvapp.util.Config
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Services {

    @GET("trending/movie/day")
    fun getTrendingMovies(
        @Query("api_key") api_key: String =Config.api_key): Call<TrendingMoviesResponse>

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") api_key: String =Config.api_key): Call<TrendingMoviesResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Query("api_key") api_key: String =Config.api_key): Call<TrendingMoviesResponse>

    @GET("movie/upcoming")
    fun getUpcomingMovies(
        @Query("api_key") api_key: String =Config.api_key): Call<TrendingMoviesResponse>

}