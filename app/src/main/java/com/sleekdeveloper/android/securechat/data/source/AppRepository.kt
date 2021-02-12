package com.sleekdeveloper.android.securechat.data.source

import androidx.lifecycle.LiveData
import com.sleekdeveloper.android.securechat.data.source.domain.User
import com.sleekdeveloper.android.securechat.data.Result

interface AppRepository {
    fun observeUserExists(): LiveData<Result<Boolean>>

    suspend fun saveUser(user: User)
}