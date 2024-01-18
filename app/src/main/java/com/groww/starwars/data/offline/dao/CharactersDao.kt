package com.groww.starwars.data.offline.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.groww.starwars.data.model.characters.Character

@Dao
interface CharactersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<Character>)

    @Query("DELETE FROM characters_details")
    suspend fun deleteArtist():Int

    @Query("SELECT * FROM characters_details")
    suspend fun getCharacters(): List<Character>
}