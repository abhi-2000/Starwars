package com.groww.starwars.presentor.ui.characters

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.groww.starwars.data.model.characters.Character
import com.groww.starwars.data.offline.dao.CharactersDao
import com.groww.starwars.data.online.ApiService
import com.kanyideveloper.starwars.utils.Constants.FIRST_PAGE_INDEX
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharactersPagingSource(
    private val apiService: ApiService, private val searchString: String, var charactersDao: CharactersDao
) :
    PagingSource<Int, Character>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val position = params.key ?: FIRST_PAGE_INDEX
        return try {
            val response = apiService.getCharacters(position)
            val characters = response.results
            CoroutineScope(Dispatchers.IO).launch{
                charactersDao.insertAll(characters)
            }

            val filteredData = characters.filter { it.name.contains(searchString, true) }

            val nextKey = position + 1
            val prevKey = if (position == 1) null else position - 1

            LoadResult.Page(data = filteredData, prevKey = prevKey, nextKey = nextKey)

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition
    }
}