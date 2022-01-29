package com.xgt.demo_networktecnologies.model


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class Tecnology (
    @SerializedName("description")
    @Expose
    val description: String,

    @SerializedName("id")
    @Expose
    val id: String,

    @SerializedName("imageUrl")
    @Expose
    val imageUrl: String,

    @SerializedName("name")
    @Expose
    val name: String
)