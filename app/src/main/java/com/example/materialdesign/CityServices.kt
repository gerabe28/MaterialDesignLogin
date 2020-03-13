package com.example.materialdesign

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface CityServices {
    @GET("gg")
    fun getCurrentCityData(@Query("endpoint") lat: String): Call<TestResponse>
    @POST("data/2.5/sendData")
    fun postCurrentCityData(@Body gg: CityResponse ): Call<CityResponse>
    @GET("gg")
    fun getCurrentData(): Call<TestResponse>

}