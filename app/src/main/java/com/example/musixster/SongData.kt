package com.example.musixster

import android.os.Parcelable
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
class SongData(
    val title: String,
    val artist: String,
    val cover: String
) : Parcelable {

}