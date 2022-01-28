package com.xgt.demo_networktecnologies.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TecnologyApi {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.160.224:3000")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val service = retrofit.create(TecnologyService::class.java)
}