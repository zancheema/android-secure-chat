package com.sleekdeveloper.android.securechat.data.source.domain

data class UserDetail(
    val phoneNumber: String,
    val firstName: String,
    val lastName: String,
    val imgUrl: String = "nothing",
    val msgToken: String = "nothing"
) {
    val fullName = "$firstName $lastName"
}