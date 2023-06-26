package com.example.alarmtest.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alarmtest.models.Kbdi
import com.example.alarmtest.network.IndexApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ViewModelMain : ViewModel() {

    private val _kbdi = MutableLiveData<List<Kbdi>>()
    val kbdi: LiveData<List<Kbdi>>
        get() = _kbdi

//    private val _date = MutableLiveData<Date>()
//    val date : LiveData<Date>
//        get() = _date

//    private val _nameCover = MutableLiveData<String>()
//    val nameCover : LiveData<String>
//        get() = _nameCover
//
//    private val _status = MutableLiveData<String>()
//    val status : LiveData<String>
//        get() = _status


    private val serviceJob = Job()
    private val serviceScope = CoroutineScope(Dispatchers.Main + serviceJob)

    init {
        getKbdi()
    }

    fun getKbdi() {
        serviceScope.launch {

            val apiDeferred = IndexApi.retrofitService.getDataAsync().await()
            try {
                val result = apiDeferred
                _kbdi.value = result.data
//                _date.value = result.data[1].date
//                _status.value= result.data[2].status
//                _nameCover.value = result.data[3].peatlandCover.name
////
//                }
            } catch (e: Exception) {
                Log.d("catch", "Error $e")
            }
//            val lamaDering: Int = dataAPI.lamaDering

//            delay(30000)
//            stopSelf()
//            Log.d(TAG, "Service dihentikan")
        }
    }

    fun changeKbdi(kbdiList: List<Kbdi>) {
        _kbdi.value = kbdiList
    }

}