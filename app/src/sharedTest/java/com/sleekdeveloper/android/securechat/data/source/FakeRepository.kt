package com.sleekdeveloper.android.securechat.data.source

import androidx.lifecycle.LiveData
import com.sleekdeveloper.android.securechat.data.Result
import com.sleekdeveloper.android.securechat.data.source.domain.User
import javax.inject.Inject

class FakeRepository @Inject constructor() : AppRepository {
    override fun observeUser(): LiveData<Result<User>> {
        TODO("Not yet implemented")
    }

    override suspend fun getUser(): Result<User> {
        TODO("Not yet implemented")
    }

    override suspend fun saveUser(user: User) {
        TODO("Not yet implemented")
    }
}