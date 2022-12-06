package com.mtpms.lr12.Interface

import com.mtpms.lr12.Model.CoinList
import com.mtpms.lr12.Model.Movie
import retrofit2.Call
import retrofit2.http.*

interface RetrofitInterface {
    @GET("marvel")
    fun getMovieList(): Call<MutableList<Movie>>
    @GET("assets")
    fun getCoinList(): Call<CoinList>
}