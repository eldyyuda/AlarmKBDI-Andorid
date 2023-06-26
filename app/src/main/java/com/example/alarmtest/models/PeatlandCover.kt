package com.example.alarmtest.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PeatlandCover (
    @Json(name = "name") var name: String = ""
): Parcelable