package com.groww.starwars.data.offline.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.groww.starwars.ListTypeConverter
import com.groww.starwars.data.model.characters.Character
import com.groww.starwars.data.model.characters.FilmResponse
import com.groww.starwars.data.offline.dao.CharactersDao
import com.groww.starwars.data.offline.dao.FilmDao

@Database(
    entities = [Character::class, FilmResponse::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(ListTypeConverter::class)
abstract class StarWarDatabase : RoomDatabase() {

    abstract fun charactersDao(): CharactersDao
    abstract fun filmDao(): FilmDao

}