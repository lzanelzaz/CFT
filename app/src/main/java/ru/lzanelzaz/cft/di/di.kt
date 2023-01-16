package ru.lzanelzaz.cft.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.lzanelzaz.cft.AppRepository
import ru.lzanelzaz.cft.api.ApiService
import ru.lzanelzaz.cft.db.AppDatabase
import ru.lzanelzaz.cft.db.BinDao

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAppRepository(
        apiService: ApiService,
        binDao: BinDao
    ): AppRepository = AppRepository(apiService = apiService, binDao = binDao)

    @Provides
    @Singleton
    fun provideApiService(): ApiService = Retrofit.Builder()
        .baseUrl("https://lookup.binlist.net")
        .addConverterFactory(MoshiConverterFactory.create())
        .build().create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase =
        Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "appDatabase"
        ).build()

    @Provides
    @Singleton
    fun provideBinDao(appDatabase: AppDatabase): BinDao = appDatabase.binDao()
}