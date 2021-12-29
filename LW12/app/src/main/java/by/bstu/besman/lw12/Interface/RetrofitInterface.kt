package by.bstu.besman.lw12.Interface

import by.bstu.besman.lw12.Model.CoinList
import by.bstu.besman.lw12.Model.Movie
import retrofit2.Call
import retrofit2.http.*

interface RetrofitInterface {
    @GET("marvel") //имя метода на сервере
    fun getMovieList(): Call<MutableList<Movie>>
    @GET("assets")
    fun getCoinList(): Call<CoinList>
}