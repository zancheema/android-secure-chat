package com.sleekdeveloper.android.securechat.data.source.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserDetail(
    val phoneNumber: String,
    val firstName: String,
    val lastName: String,
    val imgUrl: String = "nothing",
    val msgToken: String = "nothing"
) : Parcelable {
    val fullName = "$firstName $lastName"
}