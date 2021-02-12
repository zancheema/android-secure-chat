package com.sleekdeveloper.android.securechat.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.sleekdeveloper.android.securechat.data.Result
import com.sleekdeveloper.android.securechat.data.Result.Success
import com.sleekdeveloper.android.securechat.data.source.domain.User
import javax.inject.Inject

class FakeRepository @Inject constructor() : AppRepository {
    private val observableUser = MutableLiveData<User>(null)

    override fun observeUserExists(): LiveData<Result<Boolean>> =
        observableUser.map { Success(it != null) }

    override suspend fun saveUser(user: User) {
        observableUser.value = user
    }
}