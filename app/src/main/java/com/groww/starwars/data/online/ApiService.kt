package com.groww.starwars.data.online

import com.groww.starwars.data.model.characters.CharactersResponse
import com.groww.starwars.data.model.characters.FilmResponse
import com.groww.starwars.data.model.characters.HomeWorldResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {

    @GET("people/?page/")
    suspend fun getCharacters(@Query("page")page:Int): CharactersResponse
    @GET
    suspend fun getFilm(@Url url: String): FilmResponse

    @GET
    suspend fun getHomeWorld(@Url url: String): HomeWorldResponse
}