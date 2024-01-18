package com.groww.starwars.domain.repositories

import com.groww.starwars.data.model.characters.Character
import com.groww.starwars.data.offline.entity.CharactersEntity

interface Repository {

    suspend fun getCharacters(): List<Character>
    suspend fun insertAll(characters: List<CharactersEntity>)
}