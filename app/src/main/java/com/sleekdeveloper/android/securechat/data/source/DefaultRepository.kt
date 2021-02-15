package com.sleekdeveloper.android.securechat.data.source

import androidx.lifecycle.LiveData
import com.sleekdeveloper.android.securechat.data.Result
import com.sleekdeveloper.android.securechat.data.source.domain.Chat
import com.sleekdeveloper.android.securechat.data.source.domain.ChatMessage
import com.sleekdeveloper.android.securechat.data.source.domain.User
import com.sleekdeveloper.android.securechat.data.source.domain.UserDetail

class DefaultRepository : AppRepository {
    override fun getChats(): Result<List<Chat>> {
        TODO("Not yet implemented")
    }

    override fun observeChats(): LiveData<Result<List<Chat>>> {
        TODO("Not yet implemented")
    }

    override suspend fun saveUserDetail(detail: UserDetail) {
        TODO("Not yet implemented")
    }

    override suspend fun saveMessage(message: ChatMessage) {
        TODO("Not yet implemented")
    }

    override fun observeUserExists(): LiveData<Result<Boolean>> {
        TODO("Not yet implemented")
    }

    override suspend fun saveUser(user: User) {
        TODO("Not yet implemented")
    }
}