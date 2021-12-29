package by.bstu.besman.lw12.Common

import by.bstu.besman.lw12.Interface.RetrofitInterface
import by.bstu.besman.lw12.Retrofit.RetrofitClient

object CoinObj {
    private val BASE_URL2 = "https://api.coincap.io/v2/"
    val retrofitService: RetrofitInterface
        get() = RetrofitClient.getClient(BASE_URL2).create(RetrofitInterface::class.java)
}