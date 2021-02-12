package com.sleekdeveloper.android.securechat.data.source

import com.sleekdeveloper.android.securechat.data.source.domain.User
import kotlinx.coroutines.runBlocking

/**
 * Convenience function to avoid the overhead of using
 * [runBlocking] every time to save user
 */
fun AppRepository.saveUserBlocking(user: User) = runBlocking {
    this@saveUserBlocking.saveUser(user)
}