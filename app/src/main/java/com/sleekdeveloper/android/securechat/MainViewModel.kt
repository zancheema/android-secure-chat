package com.sleekdeveloper.android.securechat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.sleekdeveloper.android.securechat.data.Result
import com.sleekdeveloper.android.securechat.data.source.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    repository: AppRepository
) : ViewModel() {

    enum class AuthenticationState {
        AUTHENTICATED,
        UNAUTHENTICATED
    }

    val authenticationState = repository.observeUserExists().map { result ->
        val state = when (result) {
            is Result.Success -> {
                if (result.data) AuthenticationState.AUTHENTICATED
                else AuthenticationState.UNAUTHENTICATED
            }
            else -> AuthenticationState.UNAUTHENTICATED
        }
        Event(state)
    }
}