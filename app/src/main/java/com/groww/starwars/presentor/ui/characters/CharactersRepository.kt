package com.groww.starwars.presentor.ui.characters

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.groww.starwars.data.model.characters.Character
import com.groww.starwars.data.offline.dao.CharactersDao
import com.groww.starwars.data.offline.dao.FilmDao
import com.groww.starwars.data.online.ApiService
import com.groww.starwars.data.online.SafeApiCall
import com.kanyideveloper.starwars.utils.Constants.NETWORK_PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharactersRepository @Inject constructor(
    private val apiService: ApiService,
    private val charactersDao: CharactersDao,
    private val filmDao: FilmDao

) : SafeApiCall() {

    fun getCharacters(searchString: String): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                CharactersPagingSource(apiService, searchString, charactersDao)
            }).flow
    }

    suspend fun getFilm(url: String, name: String) = safeApiCall {
        val apiResponse=apiService.getFilm(url)
        apiResponse.name =name
        filmDao.insert(apiResponse)
       apiService.getFilm(url)
    }

    suspend fun getHomeWorld(url: String) = safeApiCall {
        apiService.getHomeWorld(url)
    }

}