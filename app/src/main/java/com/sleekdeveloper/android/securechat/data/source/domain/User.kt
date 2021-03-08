package com.sleekdeveloper.android.securechat.data.source.domain

import com.sleekdeveloper.android.securechat.data.source.local.entity.DbUser

data class User(
    val phoneNumber: String,
)

fun User.asDatabaseEntity() = DbUser(
    phoneNumber = phoneNumber
)