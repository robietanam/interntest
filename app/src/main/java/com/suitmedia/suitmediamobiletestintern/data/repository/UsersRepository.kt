package com.suitmedia.suitmediamobiletestintern.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.suitmedia.suitmediamobiletestintern.data.retrofit.ApiService
import com.suitmedia.suitmediamobiletestintern.data.retrofit.UserResponseDataItem
import com.suitmedia.suitmediamobiletestintern.data.source.UserPagingSource

class UsersRepository(private val apiService: ApiService) {
    fun getUser(): LiveData<PagingData<UserResponseDataItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                UserPagingSource(apiService)
            }
        ).liveData
    }
}