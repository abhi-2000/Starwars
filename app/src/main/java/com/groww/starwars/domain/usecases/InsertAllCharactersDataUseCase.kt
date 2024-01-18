package com.groww.starwars.domain.usecases
import com.groww.starwars.data.model.characters.Character

import com.groww.starwars.domain.repositories.Repository

class InsertAllCharactersDataUseCase(private val repository: Repository) {
    suspend fun execute(characters: List<Character>) = repository.insertAll(characters)
}