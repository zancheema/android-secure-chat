package com.sleekdeveloper.android.securechat.data.source

import androidx.lifecycle.LiveData
import com.sleekdeveloper.android.securechat.data.source.domain.User
import com.sleekdeveloper.android.securechat.data.Result
import com.sleekdeveloper.android.securechat.data.source.domain.Chat
import com.sleekdeveloper.android.securechat.data.source.domain.ChatMessage
import com.sleekdeveloper.android.securechat.data.source.domain.UserDetail

interface AppRepository {
    suspend fun getUserDetail(): Result<UserDetail>

    fun observeUserDetail(): LiveData<Result<UserDetail>>

    suspend fun isRegisteredPhoneNumber(phoneNumber: String): Result<Boolean>

    suspend fun getChats(): Result<List<Chat>>

    fun observeChats(): LiveData<Result<List<Chat>>>

    suspend fun getMessages(phoneNumber: String): Result<List<ChatMessage>>

    fun observeMessages(phoneNumber: String): LiveData<Result<List<ChatMessage>>>

    fun observeUserExists(): LiveData<Result<Boolean>>

    suspend fun saveUser(user: User)

    suspend fun saveUserDetail(detail: UserDetail)

    suspend fun saveMessage(message: ChatMessage)
}