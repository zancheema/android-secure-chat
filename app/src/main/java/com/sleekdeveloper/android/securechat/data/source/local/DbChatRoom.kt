package com.sleekdeveloper.android.securechat.data.source.local

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "chat_rooms",
    primaryKeys = ["id"]
)
data class DbChatRoom(
    @ColumnInfo(name = "id") val id: String
)
