package com.sleekdeveloper.android.securechat.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sleekdeveloper.android.securechat.data.source.local.entity.DbUser

@Dao
interface UserDao {
    @Query("SELECT * FROM users LIMIT 1")
    fun observeUser(): LiveData<DbUser?>

    @Query("SELECT * FROM users LIMIT 1")
    suspend fun getUser(): DbUser?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: DbUser)

    @Query("DELETE FROM users")
    suspend fun deleteAllUsers()

    @Delete
    suspend fun deleteUser(user: DbUser)
}
