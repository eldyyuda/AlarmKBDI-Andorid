package com.example.alarmtest.models

import com.squareup.moshi.Json

data class ListResponse(
//    @Json(name = "data")  val data : List<Duration>,
    @Json(name ="data") val data : List<Kbdi>

)

//{
//    'code' : 200,
//    'status' : "success",
//    'data' : {
//
//    }
//}
