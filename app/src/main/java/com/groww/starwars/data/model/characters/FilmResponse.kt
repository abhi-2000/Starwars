package com.groww.starwars.data.model.characters


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "film_details")
data class FilmResponse(
    @PrimaryKey(autoGenerate = true)
    val id:Long,
    @SerializedName("name")
    var name: String,
    @SerializedName("opening_crawl")
    val openingCrawl: String,
    @SerializedName("title")
    val title: String
)