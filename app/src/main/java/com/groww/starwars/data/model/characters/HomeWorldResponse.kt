package com.groww.starwars.data.model.characters


import com.google.gson.annotations.SerializedName

data class HomeWorldResponse(
    @SerializedName("name")
    val name: String
)