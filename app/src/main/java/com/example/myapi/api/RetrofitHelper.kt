package com.example.myapi.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    val retrofit = Retrofit.Builder()

        //.baseUrl("https://strangerthings-quotes.vercel.app/")
        .baseUrl("https://api.breakingbadquotes.xyz/")
        .addConverterFactory( GsonConverterFactory.create() )//json ou XML
        .build()


}