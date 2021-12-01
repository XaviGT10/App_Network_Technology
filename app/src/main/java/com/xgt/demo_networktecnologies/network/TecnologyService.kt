package com.xgt.demo_networktecnologies.network

import com.xgt.demo_networktecnologies.model.Tecnology
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TecnologyService {
    @GET("api/technology")
    fun getTechnologies(): Call<List<Tecnology>>

    @GET("api/technology/{techId}")
    fun getTecnologyById(@Path("techId") techId: String): Call<Tecnology>
}