package com.sleekdeveloper.android.securechat.data.source.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sleekdeveloper.android.securechat.data.source.domain.User

@Entity(tableName = "users")
data class DbUser(
    @PrimaryKey @ColumnInfo(name = "phone_number") val phoneNumber: String
)

fun DbUser.asDomainModel() = User(
    phoneNumber = phoneNumber
)