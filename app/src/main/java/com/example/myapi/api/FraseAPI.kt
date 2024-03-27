package com.example.myapi.api

import com.example.myapi.model.Frase
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface FraseAPI {

    //@GET("api/quotes/")
    @GET("v1/quotes")
    suspend fun recuperarFrases(
    ) : Response<List<Frase>>
}