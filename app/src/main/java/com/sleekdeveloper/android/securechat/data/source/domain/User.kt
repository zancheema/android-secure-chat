package com.sleekdeveloper.android.securechat.data.source.domain

import java.util.UUID.randomUUID

data class User(
    val phoneNumber: String,
    val uid: String = randomUUID().toString()
)