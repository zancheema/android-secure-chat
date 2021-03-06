package com.sleekdeveloper.android.securechat.data.source.local.dao

import androidx.room.*
import com.sleekdeveloper.android.securechat.data.source.local.entity.DbUserDetail

@Dao
interface UserDetailDao {
    @Query("SELECT * FROM user_details WHERE phone_number = :phoneNumber")
    suspend fun getUserDetailByPhoneNumber(phoneNumber: String): DbUserDetail?

    /**
     * Inserts new [DbUserDetail] and replaces with the new one in case of duplication
     * thus, also does the job of update
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserDetail(userDetail: DbUserDetail)
}
