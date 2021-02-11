package com.sleekdeveloper.android.securechat.data.source

import androidx.lifecycle.LiveData
import com.sleekdeveloper.android.securechat.data.source.domain.User
import com.sleekdeveloper.android.securechat.data.Result

interface AppRepository {
    fun observeUser(): LiveData<Result<User>>

    suspend fun getUser(): Result<User>

    suspend fun saveUser(user: User)
}