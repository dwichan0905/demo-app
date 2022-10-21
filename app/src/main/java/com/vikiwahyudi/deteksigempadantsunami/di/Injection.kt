package com.vikiwahyudi.deteksigempadantsunami.di

import android.content.Context
import com.vikiwahyudi.deteksigempadantsunami.data.DataGempaRepository
import com.vikiwahyudi.deteksigempadantsunami.data.local.LocalDataSource
import com.vikiwahyudi.deteksigempadantsunami.data.local.room.GempaDatabase
import com.vikiwahyudi.deteksigempadantsunami.data.remote.response.RemoteDataSource
import com.vikiwahyudi.deteksigempadantsunami.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): DataGempaRepository {
        val database = GempaDatabase.getInstance(context)
        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.gempaDao())
        val appExecutors = AppExecutors()
        return DataGempaRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}