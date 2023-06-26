package com.example.alarmtest.ui.filter

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.HttpException

class FilterViewModel : ViewModel() {
    private val _bottomSheet = MutableLiveData<Boolean>()
    val bottomSheet: LiveData<Boolean>
        get() = _bottomSheet

    fun openBottomSheet() {
        _bottomSheet.value = true
    }

    fun closeBottomSheet() {
        _bottomSheet.value = false
    }
}