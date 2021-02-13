package com.sleekdeveloper.android.securechat.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.sleekdeveloper.android.securechat.Event
import com.sleekdeveloper.android.securechat.R
import com.sleekdeveloper.android.securechat.data.source.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(repository: AppRepository) : ViewModel() {

    enum class AuthenticationState {
        AUTHENTICATED,
        UNAUTHENTICATED;

        override fun toString(): String =
            if (this == AUTHENTICATED) "Authenticated" else "Unauthenticated"
    }

    val countryCode = MutableLiveData(1)
    val countryName = MutableLiveData<String>()
    val phoneNumber = MutableLiveData<String>()
    val authenticationState = FirebaseUserLiveData().map { user ->
        if (user == null) AuthenticationState.UNAUTHENTICATED
        else AuthenticationState.AUTHENTICATED
    }

    private val _invalidCredentialsEvent = MutableLiveData<Event<Int>>()
    val invalidCredentialsEvent: LiveData<Event<Int>>
        get() = _invalidCredentialsEvent

    fun signIn() {
        val number = phoneNumber.value
        if (number == null || number.length < 10) {
            _invalidCredentialsEvent.value = Event(R.string.invalid_phone_number)
        }
    }
}