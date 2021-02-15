package com.sleekdeveloper.android.securechat.data.source.domain

import java.time.LocalDateTime

data class ChatMessage(
    val message: String,
    val from: UserDetail,
    val to: UserDetail,
    val dateTime: LocalDateTime = LocalDateTime.now(),
    val isMine: Boolean = false
)
