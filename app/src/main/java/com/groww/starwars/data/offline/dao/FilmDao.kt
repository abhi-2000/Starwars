package com.groww.starwars.data.offline.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.groww.starwars.data.model.characters.FilmResponse

@Dao
interface FilmDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(filmResponse: FilmResponse)

    @Query("DELETE FROM film_details")
    suspend fun deleteArtist():Int

    @Query("SELECT * FROM film_details")
    suspend fun getFilms(): List<FilmResponse>
}