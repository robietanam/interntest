package com.suitmedia.suitmediamobiletestintern.ui.third

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.suitmedia.suitmediamobiletestintern.data.repository.UsersRepository
import com.suitmedia.suitmediamobiletestintern.data.retrofit.UserResponseDataItem

class ThirdViewModel(private val usersRepository: UsersRepository)  : ViewModel() {

    companion object{
        private const val TAG = "UserViewModel"
    }

    val getPaging : LiveData<PagingData<UserResponseDataItem>> = usersRepository.getUser().cachedIn(viewModelScope)

}