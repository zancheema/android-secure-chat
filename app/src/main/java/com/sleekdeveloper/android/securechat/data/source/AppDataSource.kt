package com.sleekdeveloper.android.securechat.data.source

import androidx.lifecycle.LiveData
import com.sleekdeveloper.android.securechat.data.Result
import com.sleekdeveloper.android.securechat.data.source.domain.*

interface AppDataSource {
    fun observeUser(): LiveData<Result<User>>

    fun observeUserDetailByPhoneNumber(phoneNumber: String): LiveData<Result<UserDetail>>

    fun observeAllChatRooms(): LiveData<Result<List<ChatRoom>>>

    fun observeChatRoomDetailByChatRoomId(chatRoomId: String): LiveData<Result<ChatRoomDetail>>

    fun observeChatRoomMembersByChatRoomId(chatRoomId: String): LiveData<Result<List<ChatRoomMember>>>

    fun observeChatRoomMembersByPhoneNumber(phoneNumber: String): LiveData<Result<List<ChatRoomMember>>>

    suspend fun getUser(): Result<User>

    suspend fun getUserDetailByPhoneNumber(phoneNumber: String): Result<UserDetail>

    suspend fun getAllChatRooms(): Result<List<ChatRoom>>

    suspend fun getChatRoomDetailByChatRoomId(chatRoomId: String): Result<ChatRoomDetail>

    suspend fun getChatRoomMembersByChatRoomId(chatRoomId: String): Result<List<ChatRoomMember>>

    suspend fun getChatRoomMembersByPhoneNumber(phoneNumber: String): Result<List<ChatRoomMember>>

    suspend fun saveUser(user: User)

    suspend fun saveUserDetail(userDetail: UserDetail)

    suspend fun saveChatRoom(chatRoom: ChatRoom)

    suspend fun saveChatRoomDetail(detail: ChatRoomDetail)

    suspend fun saveChatRoomMember(member: ChatRoomMember)

    suspend fun deleteUser(user: User)

    suspend fun deleteAllUsers()

    suspend fun deleteChatRoom(chatRoom: ChatRoom)

    suspend fun deleteAllChatRooms()
}