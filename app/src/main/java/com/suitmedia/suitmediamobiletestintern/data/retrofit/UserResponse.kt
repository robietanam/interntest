package com.suitmedia.suitmediamobiletestintern.data.retrofit

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @field:SerializedName("page") var page: Int? = null,
    @field:SerializedName("per_page") var perPage: Int? = null,
    @field:SerializedName("total") var total: Int? = null,
    @field:SerializedName("total_pages") var totalPages: Int? = null,
    @field:SerializedName("data") var data: ArrayList<UserResponseDataItem> = arrayListOf(),
    @field:SerializedName("support") var support: UserResponseSupportItem? = UserResponseSupportItem()
)

data class UserResponseDataItem(

    @field:SerializedName("id") var id: Int? = null,
    @field:SerializedName("email") var email: String? = null,
    @field:SerializedName("first_name") var firstName: String? = null,
    @field:SerializedName("last_name") var lastName: String? = null,
    @field:SerializedName("avatar") var avatar: String? = null
)

data class UserResponseSupportItem(

    @field:SerializedName("url") var url: String? = null,
    @field:SerializedName("text") var text: String? = null

)