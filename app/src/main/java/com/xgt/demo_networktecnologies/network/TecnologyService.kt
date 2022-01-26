package com.xgt.demo_networktecnologies.network

import com.xgt.demo_networktecnologies.model.Image
import com.xgt.demo_networktecnologies.model.Tecnology
import com.xgt.demo_networktecnologies.network.request.TecnologyRequest
import retrofit2.Call
import retrofit2.http.*

interface TecnologyService {
    @GET("api/technology")
    fun getTechnologies(): Call<List<Tecnology>>

    @GET("api/technology/{techId}")
    fun getTechnologyById(@Path("techId") techId: String): Call<Tecnology>

    @POST("api/technology")
    fun createTechnology(@Body technology: TecnologyRequest): Call<Any>

    @DELETE("api/technology/{techId}")
    fun deleteTechnologyById(@Path("techId") techId: String): Call<Void>

    @GET("api/image")
    fun getImages(): Call<List<Image>>
}