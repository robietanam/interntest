package com.suitmedia.suitmediamobiletestintern.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.suitmedia.suitmediamobiletestintern.data.repository.UsersRepository
import com.suitmedia.suitmediamobiletestintern.di.Injection
import com.suitmedia.suitmediamobiletestintern.ui.third.ThirdViewModel

class UsersViewModelFactory private constructor(private val historyRepository: UsersRepository) :
    ViewModelProvider.Factory{

    companion object {
        @Volatile
        private var instance: UsersViewModelFactory? = null

        fun getInstance(context: Context): UsersViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: UsersViewModelFactory(
                    Injection.provideUserRepository(context),
                )
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(ThirdViewModel::class.java) -> {
                ThirdViewModel(historyRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}