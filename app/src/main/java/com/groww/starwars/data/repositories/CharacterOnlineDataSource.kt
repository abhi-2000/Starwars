package com.groww.starwars.data.repositories

import com.groww.starwars.data.model.characters.CharactersResponse

interface CharacterOnlineDataSource {

    suspend fun getCharactersFromApi():CharactersResponse
}