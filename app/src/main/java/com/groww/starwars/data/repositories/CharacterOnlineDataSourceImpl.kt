package com.groww.starwars.data.repositories

import com.groww.starwars.data.model.characters.CharactersResponse
import com.groww.starwars.data.online.ApiService

class CharacterOnlineDataSourceImpl(private val apiServices: ApiService) : CharacterOnlineDataSource {
    override suspend fun getCharactersFromApi(): CharactersResponse {
        return apiServices.getCharacters(1)
    }
}