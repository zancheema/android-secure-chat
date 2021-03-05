package com.sleekdeveloper.android.securechat.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [DbUser::class, DbUserDetail::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun userDetailDao(): UserDetailDao
}
