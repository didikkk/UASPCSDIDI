package com.PCSUASDIDI.PCSUASDIDI.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize

data class User (
    var login: String = "",
    var avatarUrl: String = "",
    var htmlUrl: String = "",
    var followersUrl: String = "",
    var followers: Int = 0,
    var followingsUrl: String = "",
    var following: Int = 0,
    var repoUrl: String = "",
    var repos: Int = 0
) : Parcelable