package com.xgt.demo_networktecnologies.network

import com.xgt.demo_networktecnologies.model.Tecnology
import retrofit2.Call
import retrofit2.http.GET

interface TecnologyService {
    @GET("api/technology")
    fun getTechnologies(): Call<List<Tecnology>>
}