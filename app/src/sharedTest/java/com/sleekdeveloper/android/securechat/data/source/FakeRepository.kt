package com.sleekdeveloper.android.securechat.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.sleekdeveloper.android.securechat.data.Result
import com.sleekdeveloper.android.securechat.data.Result.Success
import com.sleekdeveloper.android.securechat.data.source.domain.Chat
import com.sleekdeveloper.android.securechat.data.source.domain.ChatMessage
import com.sleekdeveloper.android.securechat.data.source.domain.User
import com.sleekdeveloper.android.securechat.data.source.domain.UserDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FakeRepository @Inject constructor() : AppRepository {
    private val observableUser = MutableLiveData<User>(null)
    private var observableUserDetail = MutableLiveData<UserDetail>(null)
    private val observableChatMessages = MutableLiveData<List<ChatMessage>>()

    override fun getChats(): Result<List<Chat>> {
        TODO("Not yet implemented")
    }

    override fun observeChats(): LiveData<Result<List<Chat>>> =
        observableChatMessages.map { messages ->
            Success(getChats(messages, observableUserDetail.value!!))
        }

    private fun getChats(messages: List<ChatMessage>, me: UserDetail): List<Chat> {
        val chatHeads = mutableMapOf<String, Chat>()
        for (msg in messages) {
            val isMine = msg.from == me
            val other = if (isMine) msg.to else msg.from
            val chatHead = chatHeads[other.phoneNumber]
            if (chatHead == null || msg.dateTime >= chatHead.dateTime) {
                chatHeads[other.phoneNumber] = getChat(msg, isMine)
            }
        }

        return chatHeads.values.toList()
    }

    private fun getChat(
        msg: ChatMessage,
        isMine: Boolean
    ): Chat {
        val other = if (isMine) msg.to else msg.from
        return Chat(
            other.fullName,
            other.phoneNumber,
            msg.message,
            msg.dateTime
        )
    }

    override fun observeUserExists(): LiveData<Result<Boolean>> =
        observableUser.map { Success(it != null) }

    override suspend fun saveUser(user: User) = withContext(Dispatchers.Main) {
        observableUser.value = user
    }

    override suspend fun saveUserDetail(detail: UserDetail) {
        observableUserDetail.value = detail
    }

    override suspend fun saveMessage(message: ChatMessage) {
        val tmp = observableChatMessages.value?.toMutableList() ?: mutableListOf()
        tmp.add(message)
        withContext(Dispatchers.Main) {
            observableChatMessages.value = tmp
        }
    }
}