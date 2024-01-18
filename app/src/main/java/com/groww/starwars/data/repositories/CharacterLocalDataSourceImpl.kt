package com.groww.starwars.data.repositories

import com.groww.starwars.data.model.characters.Character
import com.groww.starwars.data.model.characters.CharactersResponse
import com.groww.starwars.data.offline.dao.CharactersDao

class CharacterLocalDataSourceImpl(private val characterDao: CharactersDao) : CharacterLocalDataSource {
    override suspend fun getCharactersFromLocalSource(): CharactersResponse {
        TODO("Not yet implemented")
    }
//    override suspend fun getCharactersFromLocalSource(): CharactersResponse {
//        return characterDao.getCharacters()
//    }

    override suspend fun insertAllCharacters(characters: List<Character>) {
        characterDao.insertAll(characters)
    }
}