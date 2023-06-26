package com.example.alarmtest.models

import com.squareup.moshi.Json
data class Duration(
    @Json(name = "jenis_dering") val jenisDering: String = "",
    @Json(name = "lama_dering") val lamaDering: Int = 0
)
