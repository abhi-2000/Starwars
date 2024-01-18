package com.groww.starwars.presentor.ui.characterDetails


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.groww.starwars.presentor.ui.characters.CharactersRepository
import com.groww.starwars.data.model.characters.Character
import com.groww.starwars.data.model.characters.FilmResponse
import com.groww.starwars.data.model.characters.HomeWorldResponse
import com.kanyideveloper.starwars.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val charactersRepository: CharactersRepository,
    savedStateHandle: SavedStateHandle
) :
    ViewModel() {

    private val myArguments = savedStateHandle.get<Character>("Character")

    private val _details = MutableLiveData<Character>()
    val details: LiveData<Character>
        get() = _details

    private val _homeWorld = MutableStateFlow<Resource<HomeWorldResponse>>(Resource.Empty())
    val homeWorldResponse: StateFlow<Resource<HomeWorldResponse>>
        get() = _homeWorld

    private val _filmDetails = MutableStateFlow<Resource<List<FilmResponse>>>(Resource.Empty())
    val filmResponseDetails: StateFlow<Resource<List<FilmResponse>>>
        get() = _filmDetails

    private val filmsList: ArrayList<FilmResponse> = ArrayList()

    init {
        _details.value = myArguments!!
        getHomeWorldData(myArguments.homeworld)
        getFilmData()
    }

    private fun getFilmData() {
        myArguments!!.films.forEach { film ->

            viewModelScope.launch(Dispatchers.IO) {
                _filmDetails.value = Resource.Loading()
                when (val characterDetailsResponse = charactersRepository.getFilm(film,myArguments.name)) {
                    is Resource.Failure -> {
                        _filmDetails.value =
                            Resource.Failure(characterDetailsResponse.message!!)
                    }
                    is Resource.Success -> {
                        if (characterDetailsResponse.data == null) {
                            _filmDetails.value = Resource.Failure("Empty Film Response List")
                        } else {
                            filmsList.add(characterDetailsResponse.data)
                            _filmDetails.value = Resource.Success(filmsList)
                        }
                    }

                    is Resource.Empty -> TODO()
                    is Resource.Loading -> TODO()
                }
            }
        }
    }


    private fun getHomeWorldData(homeWorldUrl: String) {

        viewModelScope.launch(Dispatchers.IO) {
            _filmDetails.value = Resource.Loading()
            when (val homeWorldResponse = charactersRepository.getHomeWorld(homeWorldUrl)) {
                is Resource.Failure -> {
                    _homeWorld.value = Resource.Failure(homeWorldResponse.message!!)
                }
                is Resource.Success -> {
                    if (homeWorldResponse.data == null) {
                        _homeWorld.value = Resource.Failure("N/A")
                    } else {
                        _homeWorld.value = Resource.Success(homeWorldResponse.data)
                    }
                }

                is Resource.Empty -> TODO()
                is Resource.Loading -> TODO()
            }
        }
    }
}