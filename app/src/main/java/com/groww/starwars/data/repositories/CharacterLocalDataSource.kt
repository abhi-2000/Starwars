package com.groww.starwars.data.repositories

import com.groww.starwars.data.model.characters.Character
import com.groww.starwars.data.model.characters.CharactersResponse

interface CharacterLocalDataSource {

    suspend fun getCharactersFromLocalSource():CharactersResponse
    suspend fun insertAllCharacters(characters: List<Character>)

}