package com.mtpms.lr12.Common

import com.mtpms.lr12.Interface.RetrofitInterface
import com.mtpms.lr12.Retrofit.RetrofitCoinClient

object Common {
    private val BASE_URL = "https://www.simplifiedcoding.net/demos/"
    val retrofitService: RetrofitInterface
        get() = RetrofitCoinClient.getCoin(BASE_URL).create(RetrofitInterface::class.java)
}