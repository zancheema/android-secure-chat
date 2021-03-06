package com.sleekdeveloper.android.securechat.data.source.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.sleekdeveloper.android.securechat.data.source.local.entity.DbUser

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE phone_number = :phoneNumber")
    suspend fun getUserByPhoneNumber(phoneNumber: String): DbUser?

    @Insert
    suspend fun insertUser(user: DbUser)

    @Delete
    suspend fun deleteUser(user: DbUser)

    @Query("DELETE FROM users WHERE phone_number = :phoneNumber")
    suspend fun deleteUserByPhoneNumber(phoneNumber: String)
}
