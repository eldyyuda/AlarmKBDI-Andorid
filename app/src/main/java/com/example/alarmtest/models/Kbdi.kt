package com.example.alarmtest.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import java.util.*
@Parcelize
data class Kbdi(
//    @Json(name = "id_kbdi") val idKbdi: Int = 0,
    @Json(name = "id") var id: Int = 1,
    @Json(name = "kbdi") var kbdi: Float = 0f,
    @Json(name = "status") var status: String = "",
    @Json(name = "date") var date: String = "",
    @Json(name = "peatland_cover") var peatlandCover: PeatlandCover,
    var isActive: Boolean = false
): Parcelable
