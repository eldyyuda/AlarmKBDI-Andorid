package com.example.alarmtest.alarm

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.AnyRes
import androidx.annotation.NonNull
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.alarmtest.R
import com.example.alarmtest.ui.home.HomeFragment
import java.util.*


//import android.R


class Alarm : BroadcastReceiver() {
    companion object {
        const val TYPE_ONE_TIME = "OneTimeAlarm"
        const val TYPE_REPEATING = "Early Warning System"
        const val EXTRA_MESSAGE = "message"
        const val EXTRA_TYPE = "type"
        const val EXTRA_STATUS = "status"
        private const val NOTIFICATION_ID = 32

        // Siapkan 2 id untuk 2 macam alarm, onetime dan repeating
        private const val ID_ONETIME = 100
        private const val ID_REPEATING = 101
        private const val DATE_FORMAT = "yyyy-MM-dd"
        private const val TIME_FORMAT = "HH:mm"

    }


    override fun onReceive(context: Context, intent: Intent) {
        val type = intent.getStringExtra(EXTRA_TYPE)
        val message = intent.getStringExtra("message")
        val title =
            if (type.equals(TYPE_ONE_TIME, ignoreCase = true)) TYPE_ONE_TIME else TYPE_REPEATING
        val notifId =
            if (type.equals(TYPE_ONE_TIME, ignoreCase = true)) ID_ONETIME else ID_REPEATING
        val status = intent.getStringExtra("status")
        if (message != null && status != null) {
            showAlarmNotification(context, title, message, notifId, status)

        }
    }

    fun setRepeatingAlarm(context: Context, type: String, message: String, status: String) {
//        if (isDateInvalid(time, TIME_FORMAT)) return

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, Alarm::class.java)
        intent.putExtra("message", message)
        intent.putExtra("status", status)
        val putExtra = intent.putExtra(EXTRA_TYPE, type)
//        val timeArray = time.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
//        Log.d("cek",time)
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 23)
        calendar.set(Calendar.MINUTE, 46)
        calendar.set(Calendar.SECOND, 0)
        val pendingIntent =
            PendingIntent.getBroadcast(context, ID_REPEATING, intent, PendingIntent.FLAG_IMMUTABLE)
        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
        Toast.makeText(context, "Repeating alarm set up", Toast.LENGTH_SHORT).show()
    }

    private fun showAlarmNotification(
        context: Context,
        title: String,
        message: String,
        notifId: Int,
        status: String
    ) {
//        val channelId = "Channel_1"
//        val channelName = "AlarmManager channel"
        var notificationId = 1
        var channelId = "channel_low"
        var channelName = "low channel"
        val notificationManagerCompat =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        var sound = Uri.parse("android.resource://" + context.packageName + "/" + R.raw.low)

//        val url =
//            Uri.parse("android.resource://" + getPackageName().toString() + "/" + R.raw.usa_for_africa_we_are_the_world)
//        val file = File(url.toString())

//        val defaultSoundUri = RingtoneManager.getDefaultUri(com.example.alarmtest.R.raw.low)
//        val uri =
//            UriUtils.getUriToResource(context, R.raw.low)

//        Ringtone r = RingtoneManager.getRingtone(this@Alarm,defaultSoundUri)
//        val ringtoneUri = RingtoneManager.getDefaultUri(R.raw.low)
////        val ringtoneSound: Ringtone = RingtoneManager.getRingtone(requireContext(ContentProvider), ringtoneUri)
//        ringtoneSound.play()
////        else if(status =="Moderate")
//            defaultSoundUri = RingtoneManager.getDefaultUri(R.raw.moderate)
////        else if(status=="High")
//            defaultSoundUri = RingtoneManager.getDefaultUri(R.raw.hight)
////        else if(status=="Extreme")
//            defaultSoundUri = RingtoneManager.getDefaultUri(R.raw.extreme)
        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(com.example.alarmtest.R.drawable.ic_access_time_black)
            .setContentTitle(title)
            .setContentText(message)
            .setColor(ContextCompat.getColor(context, android.R.color.transparent))
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
            .setSound(UriUtils.getUriToResource(context, R.raw.low))


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val soundAttributes = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .build()

            val statusTrim = status.lowercase().trim()
            Log.d("negro", "status diatas $statusTrim")
            if (statusTrim == "moderate") {
                notificationId = 2
                channelId = "channel_moderate"
                channelName = "moderate channel"
                sound =
                    Uri.parse("android.resource://" + context.packageName + "/" + R.raw.moderate)
                Log.d("negro", "Moderate")
            } else if (statusTrim == "high") {
                notificationId = 3
                channelId = "channel_high"
                channelName = "high channel"
                sound =
                    Uri.parse("android.resource://" + context.packageName + "/" + R.raw.hight)
                Log.d("negro", "High")
            } else if (statusTrim == "extreme") {
                notificationId = 4
                channelId = "channel_extreme"
                channelName = "extreme channel"
                sound =
                    Uri.parse("android.resource://" + context.packageName + "/" + R.raw.extreme)
                Log.d("negro", "Extreme")
            }
            Log.d("negro", "channel id $channelId dan status : $status trim $statusTrim")
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT
            )
//            channel.enableVibration(true)
//            channel.vibrationPattern = longArrayOf(1000, 1000, 1000, 1000, 1000)
//            builder.setChannelId(channelId)
//            notificationManagerCompat.createNotificationChannel(channel)
            channel.description = channelName
            channel.setSound(sound, soundAttributes)
            builder.setChannelId(channelId)
            notificationManagerCompat.createNotificationChannel(channel)
        }
//        val notification = builder.build()
//        notificationManagerCompat.notify(notifId, notification)
        val notification = builder.build()

        notificationManagerCompat.notify(notificationId, notification)
    }

    fun setRepeatingAlarm(context: Context?, type: String) {

    }
}

object UriUtils {
    @Throws(Resources.NotFoundException::class)
    fun getUriToResource(
        @NonNull context: Context,
        @AnyRes resId: Int
    ): Uri {
        val res: Resources = context.resources
        return Uri.parse(
            ContentResolver.SCHEME_ANDROID_RESOURCE +
                    "://" + res.getResourcePackageName(resId)
                    + '/' + res.getResourceTypeName(resId)
                    + '/' + res.getResourceEntryName(resId)
        )
    }
}
