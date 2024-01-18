package com.groww.starwars.data.model.characters


import com.google.gson.annotations.SerializedName

data class CharactersResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: String,
    @SerializedName("results")
    var results: List<Character>

)