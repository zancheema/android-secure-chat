package com.sleekdeveloper.android.securechat.chats

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.sleekdeveloper.android.securechat.data.Result.Success
import com.sleekdeveloper.android.securechat.data.source.AppRepository
import com.sleekdeveloper.android.securechat.data.source.domain.Chat
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChatsViewModel @Inject constructor(
    repository: AppRepository
) : ViewModel() {
    val chats: LiveData<List<Chat>> = repository.observeChats().map { result ->
        when (result) {
            is Success -> result.data
            else -> emptyList()
        }
    }
}