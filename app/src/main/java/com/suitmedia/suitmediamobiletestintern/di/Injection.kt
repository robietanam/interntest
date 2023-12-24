package com.suitmedia.suitmediamobiletestintern.di

import android.content.Context
import com.suitmedia.suitmediamobiletestintern.data.repository.UsersRepository
import com.suitmedia.suitmediamobiletestintern.data.retrofit.ApiConfig


object Injection {
    fun provideUserRepository(context: Context): UsersRepository {
        val apiService = ApiConfig.getApiService(context)
        return UsersRepository(apiService)
    }
}