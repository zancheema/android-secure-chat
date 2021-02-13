package com.sleekdeveloper.android.securechat.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sleekdeveloper.android.securechat.Event
import com.sleekdeveloper.android.securechat.R
import com.sleekdeveloper.android.securechat.data.source.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(repository: AppRepository) : ViewModel() {
    val countryCode = MutableLiveData(1)
    val phoneNumber = MutableLiveData<String>()

    private val _invalidCredentialsEvent = MutableLiveData<Event<Int>>()
    val invalidCredentialsEvent: LiveData<Event<Int>>
        get() = _invalidCredentialsEvent

    fun verifyPhoneNumber() {
        val number = phoneNumber.value
        if (number == null || number.length < 10) {
            _invalidCredentialsEvent.value = Event(R.string.invalid_phone_number)
        }
    }
}