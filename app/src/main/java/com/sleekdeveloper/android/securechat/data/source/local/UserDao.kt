package com.sleekdeveloper.android.securechat.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.*

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
