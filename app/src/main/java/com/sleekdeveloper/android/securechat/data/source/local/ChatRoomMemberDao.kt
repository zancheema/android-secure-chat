package com.sleekdeveloper.android.securechat.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ChatRoomMemberDao {
    @Query("SELECT * FROM chat_room_members WHERE chat_room_id = :chatRoomId")
    suspend fun getChatRoomMembersByChatRoomId(chatRoomId: String): List<DbChatRoomMember>

    @Query("SELECT * FROM chat_room_members WHERE phone_number = :phoneNumber")
    fun getChatRoomMembersByPhoneNumber(phoneNumber: String): List<DbChatRoomMember>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertChatRoomMember(member: DbChatRoomMember)
}