package com.xgt.demo_networktecnologies.network.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TecnologyRequest(
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("description")
    @Expose
    val description: String,
    @SerializedName("imageUrl")
    @Expose
    val imageUrl: String = ""
)