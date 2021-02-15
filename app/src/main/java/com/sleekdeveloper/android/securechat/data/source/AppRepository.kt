package com.sleekdeveloper.android.securechat.data.source

import androidx.lifecycle.LiveData
import com.sleekdeveloper.android.securechat.data.source.domain.User
import com.sleekdeveloper.android.securechat.data.Result
import com.sleekdeveloper.android.securechat.data.source.domain.Chat
import com.sleekdeveloper.android.securechat.data.source.domain.ChatMessage
import com.sleekdeveloper.android.securechat.data.source.domain.UserDetail

interface AppRepository {
    fun getChats(): Result<List<Chat>>

    fun observeChats(): LiveData<Result<List<Chat>>>

    fun observeUserExists(): LiveData<Result<Boolean>>

    suspend fun saveUser(user: User)

    suspend fun saveUserDetail(detail: UserDetail)

    suspend fun saveMessage(message: ChatMessage)
}