package com.mtpms.lr12.Common

import com.mtpms.lr12.Interface.RetrofitInterface
import com.mtpms.lr12.Retrofit.RetrofitClient

object CoinObj {
    private val BASE_URL2 = "https://api.coincap.io/v2/"
    val retrofitService: RetrofitInterface
        get() = RetrofitClient.getClient(BASE_URL2).create(RetrofitInterface::class.java)
}