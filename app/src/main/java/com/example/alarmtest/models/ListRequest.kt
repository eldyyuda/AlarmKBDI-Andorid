package com.example.alarmtest.models

import com.squareup.moshi.Json

data class ListRequest(
    @Json(name="item") val item : List<Item>
)
