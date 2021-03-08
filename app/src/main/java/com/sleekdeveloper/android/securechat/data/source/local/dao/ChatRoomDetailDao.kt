package com.sleekdeveloper.android.securechat.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sleekdeveloper.android.securechat.data.source.local.entity.DbChatRoomDetail

@Dao
interface ChatRoomDetailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChatRoomDetail(detail: DbChatRoomDetail)

    @Query("SELECT * FROM chat_room_details WHERE chat_room_id = :chatRoomId")
    suspend fun getChatRoomDetailByChatRoomId(chatRoomId: String): DbChatRoomDetail?
}
