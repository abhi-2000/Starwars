package com.groww.starwars.domain.usecases

import com.groww.starwars.domain.repositories.Repository

class GetAllCharactersDataUseCase(private val repository: Repository) {

    suspend fun execute()=repository.getCharacters()
}