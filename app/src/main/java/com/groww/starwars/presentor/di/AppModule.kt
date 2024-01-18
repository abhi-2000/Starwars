package com.groww.starwars.presentor.di

import com.groww.starwars.data.offline.dao.CharactersDao
import com.groww.starwars.data.offline.dao.FilmDao
import com.groww.starwars.data.online.ApiService
import com.groww.starwars.data.repositories.CharacterLocalDataSource
import com.groww.starwars.data.repositories.CharacterLocalDataSourceImpl
import com.groww.starwars.data.repositories.CharacterOnlineDataSource
import com.groww.starwars.data.repositories.CharacterOnlineDataSourceImpl
import com.groww.starwars.domain.repositories.Repository
import com.groww.starwars.domain.repositories.RepositoryImpl
import com.groww.starwars.presentor.ui.characters.CharactersRepository
import com.kanyideveloper.starwars.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object AppModule {

    @Singleton
    @Provides
    fun providesBaseUrl(): String {
        return BASE_URL
    }

    @Singleton
    @Provides
    fun provideLocalDataSource(charactersDao: CharactersDao): CharacterLocalDataSource {
        return CharacterLocalDataSourceImpl(characterDao = charactersDao)

    }


    @Singleton
    @Provides
    fun provideOnlineDataSource(apiService: ApiService): CharacterOnlineDataSourceImpl {
        return CharacterOnlineDataSourceImpl(apiService)

    }

    @Singleton
    @Provides
    fun provideRepository(
        localDataSource: CharacterLocalDataSource,
        onlineDataSource: CharacterOnlineDataSource
    ): Repository {
        return RepositoryImpl(localDataSource, onlineDataSource)

    }

    @Singleton
    @Provides
    fun providesCharactersRepository(apiService: ApiService,charactersDao: CharactersDao,filmDao:FilmDao): CharactersRepository {
        return CharactersRepository(apiService,charactersDao,filmDao)
    }



//    @Singleton
//    @Provides
//    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
//        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
//    }

    @Singleton
    @Provides
    fun providesConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    private val interceptor= HttpLoggingInterceptor().apply {
        this.level=HttpLoggingInterceptor.Level.BODY
    }

    private val client=OkHttpClient.Builder().apply {
        this.addInterceptor(interceptor)
            .connectTimeout(30,TimeUnit.SECONDS)
            .readTimeout(20,TimeUnit.SECONDS)
            .writeTimeout(25,TimeUnit.SECONDS)
    }.build()

    @Singleton
    @Provides
    fun providesRetrofit(
        baseUrl: String,
        converterFactory: Converter.Factory
    ): Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(converterFactory)
            .client(client)

        return retrofit.build()
    }

    @Singleton
    @Provides
    fun providesApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}