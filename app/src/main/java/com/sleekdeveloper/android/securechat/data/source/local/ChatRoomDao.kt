package com.sleekdeveloper.android.securechat.data.source.local

import androidx.room.*

@Dao
interface ChatRoomDao {
    @Query("SELECT * FROM chat_rooms")
    suspend fun getAllChatRooms(): List<DbChatRoom>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertChatRoom(chatRoom: DbChatRoom)

    @Delete
    fun deleteChatRoom(chatRoom: DbChatRoom)

    @Query("DELETE FROM chat_rooms")
    suspend fun deleteAllChatRooms()
}
