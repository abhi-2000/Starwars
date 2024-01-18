package com.groww.starwars.domain.repositories

import com.groww.starwars.data.model.characters.Character

interface Repository {

    suspend fun getCharacters(): List<Character>
    suspend fun insertAll(characters: List<Character>)
}