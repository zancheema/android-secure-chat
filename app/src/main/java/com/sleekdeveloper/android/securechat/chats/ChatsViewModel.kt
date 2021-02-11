package com.sleekdeveloper.android.securechat.chats

import androidx.lifecycle.ViewModel
import com.sleekdeveloper.android.securechat.data.source.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@HiltViewModel
class ChatsViewModel @Inject constructor(
    repository: AppRepository
) : ViewModel() {
    // TODO: Implement the ViewModel
}