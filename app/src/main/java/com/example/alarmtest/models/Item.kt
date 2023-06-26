package com.example.alarmtest.models

import com.squareup.moshi.Json

data class Item(
    @Json(name = "name") val name : String = "",
    @Json(name = "price") val price : Float= 0.0f,
    @Json(name = "description") val curahHujan : String="",
    @Json(name ="on_offer") val on_offer: Boolean = false
)
