package com.example.alarmtest.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.os.IBinder
import android.util.Log
import com.example.alarmtest.alarm.Alarm
import com.example.alarmtest.models.KbdiPreference
import com.example.alarmtest.network.IndexApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AlarmService : Service() {
    private lateinit var alarmReceiver: Alarm

    //    private lateinit var dataAPI : GetAPI

    companion object {
        internal val TAG = AlarmService::class.java.simpleName

        // Siapkan 2 id untuk 2 macam alarm, onetime dan repeating
        private const val ID_ONETIME = 100
        private const val ID_REPEATING = 101
        private const val DATE_FORMAT = "yyyy-MM-dd"
        private const val TIME_FORMAT = "HH:mm"
    }

    //    override fun onBind(intent: Intent): IBinder {
//        TODO("Return the communication channel to the service.")
//    }
    private val serviceJob = Job()
    private val serviceScope = CoroutineScope(Dispatchers.Main + serviceJob)
    override fun onBind(intent: Intent): IBinder? {
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.d(TAG, "Service dijalankan...")
        serviceScope.launch {
//            ringtone = MainActivity()
////          ringtone.playtune()
//            dataAPI = GetAPI()
//            dataAPI.getMydata()
//            val apiDeferred = IndexApi.retrofitService.getDataAsync()
            try {
                val mp: MediaPlayer
                val notif = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                mp = MediaPlayer.create(this@AlarmService, notif)
//                val result = apiDeferred.await()
//                val kbdi = result.data[0].kbdi
//                val date = result.data[1].date
//                val status = result.data[2].status
//                val nameCover = result.data[3].peatlandCover.name

                val kbdiPreference = KbdiPreference(this@AlarmService).getKbdi()

//                Log.d("tes", "${kbdi}")
//                val timeSec = System.currentTimeMillis() + (lamaDering * 1000)
//                Log.d("time","${timeSec}")
                alarmReceiver = Alarm()
                val messageNotif =
                    "Lahan ${kbdiPreference.peatlandCover.name} dengan status ${kbdiPreference.status}"
                Log.d("ini",messageNotif)
                val status = kbdiPreference.status
                Log.e("cina",status)
                alarmReceiver.setRepeatingAlarm(baseContext, Alarm.TYPE_REPEATING, messageNotif,status)

//                if () {
//                    Log.d("time","didalam if ${System.currentTimeMillis()}")
//                    mp.stop()
//                    stopSelf()
//                } else {
//
//                    Log.d("time","didalam else ${System.currentTimeMillis()}")
////                  mp.setDataSource(this, notif)
//                    mp.isLooping = true
//                    mp.start()
//
//                }
            } catch (e: Exception) {
                Log.d("coba", "Error $e")
            }
//            val lamaDering: Int = dataAPI.lamaDering

//            delay(30000)
//            stopSelf()
//            Log.d(TAG, "Service dihentikan")
        }
        return START_STICKY
    }


    override fun onDestroy() {
        super.onDestroy()
        serviceJob.cancel()
        Log.d(TAG, "onDestroy: ")
    }

}