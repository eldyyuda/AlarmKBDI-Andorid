package com.example.alarmtest.utils

import android.content.Context
import android.net.Uri
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.MultipartBody.Part.Companion.createFormData
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody

class UriUtils {
    fun uriToMultipartBodyPart(context: Context, fileName: String, uri: String): MultipartBody.Part {
        val file = FileUtils.getFile(context, Uri.parse(uri))

        val requestFile: RequestBody =
            file.asRequestBody("multipart/form-data".toMediaTypeOrNull())

        return createFormData(fileName, file.name, requestFile)
    }
}