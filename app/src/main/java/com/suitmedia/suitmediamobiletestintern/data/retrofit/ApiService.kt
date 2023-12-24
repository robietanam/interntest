package com.suitmedia.suitmediamobiletestintern.data.retrofit



import retrofit2.http.*

interface ApiService {

    @GET("users")
    suspend fun getUsersPaging(
        @Query("page") page: Int? = null,
        @Query("per_page") size: Int? = null,
        ) : UserResponse

}