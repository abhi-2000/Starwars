package com.groww.starwars.domain.repositories

import com.groww.starwars.data.model.characters.Character
import com.groww.starwars.data.offline.entity.CharactersEntity
import com.groww.starwars.data.repositories.CharacterLocalDataSource
import com.groww.starwars.data.repositories.CharacterOnlineDataSource

class RepositoryImpl(
    private val localDataSource: CharacterLocalDataSource,
    private val onlineDataSource: CharacterOnlineDataSource
) : Repository {
    override suspend fun getCharacters(): List<Character> {
        TODO("Not yet implemented")
    }

    override suspend fun insertAll(characters: List<CharactersEntity>) {
        TODO("Not yet implemented")
    }
}