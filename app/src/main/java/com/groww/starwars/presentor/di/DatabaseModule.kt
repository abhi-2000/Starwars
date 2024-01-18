package com.groww.starwars.presentor.di

import android.app.Application
import androidx.room.Room
import com.groww.starwars.data.offline.dao.CharactersDao
import com.groww.starwars.data.offline.dao.FilmDao
import com.groww.starwars.data.offline.database.StarWarDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideLocalDatabase(context:Application):StarWarDatabase{
        return Room.databaseBuilder(context,StarWarDatabase::class.java,"StarWarDb").fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideCharactersDao(starWarDatabase: StarWarDatabase):CharactersDao{
        return starWarDatabase.charactersDao()
    }

    @Singleton
    @Provides
    fun provideFilmDao(starWarDatabase: StarWarDatabase):FilmDao{
        return starWarDatabase.filmDao()
    }

}