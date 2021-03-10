package com.sleekdeveloper.android.securechat.data.source.domain

import java.time.LocalDateTime
import java.util.UUID.randomUUID

data class ChatMessage(
    val message: String,
    val from: UserDetail,
    val to: UserDetail,
    val id: String = randomUUID().toString(),
    val dateTime: LocalDateTime = LocalDateTime.now(),
    val isMine: Boolean = false
)
