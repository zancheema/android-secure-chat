package com.sleekdeveloper.android.securechat.data.source.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

/**
 * Detail for [DbUser]
 *
 * @property phoneNumber is [ForeignKey] to [DbUser.phoneNumber]
 * and is automatically deleted when [DbUser.phoneNumber] is deleted
 */
@Entity(
    tableName = "user_details",
    primaryKeys = ["phone_number"],
    foreignKeys = [
        ForeignKey(
            entity = DbUser::class,
            parentColumns = ["phone_number"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.RESTRICT,
            childColumns = ["phone_number"]
        )
    ]
)
data class DbUserDetail(
    @ColumnInfo(name = "phone_number") val phoneNumber: String,
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    @ColumnInfo(name = "photo_url") val photoUrl: String
)
