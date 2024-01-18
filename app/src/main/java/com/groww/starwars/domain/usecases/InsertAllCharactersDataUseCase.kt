package com.groww.starwars.domain.usecases

import com.groww.starwars.data.offline.entity.CharactersEntity
import com.groww.starwars.domain.repositories.Repository

class InsertAllCharactersDataUseCase(private val repository: Repository) {
    suspend fun execute(characters: List<CharactersEntity>) = repository.insertAll(characters)
}