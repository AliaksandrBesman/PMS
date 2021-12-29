package by.bstu.besman.lw12.Common

import by.bstu.besman.lw12.Interface.RetrofitInterface
import by.bstu.besman.lw12.Retrofit.RetrofitCoinClient

object Common {
    private val BASE_URL = "https://www.simplifiedcoding.net/demos/"
    val retrofitService: RetrofitInterface
        get() = RetrofitCoinClient.getCoin(BASE_URL).create(RetrofitInterface::class.java)
}