package com.sleekdeveloper.android.securechat.auth.verify

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VerifyCodeViewModel @Inject constructor() : ViewModel() {
    val phoneNumber = MutableLiveData<String>()
}