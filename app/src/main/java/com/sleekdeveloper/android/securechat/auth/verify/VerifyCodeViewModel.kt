package com.sleekdeveloper.android.securechat.auth.verify

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.sleekdeveloper.android.securechat.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VerifyCodeViewModel @Inject constructor() : ViewModel() {

    val phoneNumber = MutableLiveData<String>()
    val verificationId = MutableLiveData<String>()
    val smsCode = MutableLiveData<String>()

    private val _verificationEvent = MutableLiveData<Event<AuthCredential>>()
    val verificationEvent: LiveData<Event<AuthCredential>>
        get() = _verificationEvent

    fun verify() {
        _verificationEvent.value =
            Event(PhoneAuthProvider.getCredential(verificationId.value!!, smsCode.value!!))
    }
}