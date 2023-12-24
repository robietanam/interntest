package com.suitmedia.suitmediamobiletestintern.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.suitmedia.suitmediamobiletestintern.data.retrofit.ApiService
import com.suitmedia.suitmediamobiletestintern.data.retrofit.UserResponseDataItem

class UserPagingSource(private val apiService: ApiService) : PagingSource<Int, UserResponseDataItem>() {

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }

    override fun getRefreshKey(state: PagingState<Int, UserResponseDataItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserResponseDataItem> {
        return try {
            val position = params.key ?: INITIAL_PAGE_INDEX
            val responseData = apiService.getUsersPaging(position, params.loadSize)
            LoadResult.Page(
                data = responseData.data,
                prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
                nextKey = if (responseData.data.isNullOrEmpty()) null else position + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

}