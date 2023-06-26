package com.example.alarmtest.ui.home

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.lifecycle.ViewModelProvider
import com.example.alarmtest.BaseFragmentBinding
import com.example.alarmtest.R
import com.example.alarmtest.alarm.UriUtils
import com.example.alarmtest.databinding.HomeFragmentBinding
import com.example.alarmtest.models.Kbdi
import com.example.alarmtest.models.KbdiPreference
import com.example.alarmtest.models.PeatlandCover
import com.example.alarmtest.ui.filter.FilterBottomSheet
import com.example.alarmtest.ui.filter.FilterViewModel


class HomeFragment : BaseFragmentBinding<HomeFragmentBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> HomeFragmentBinding =
        HomeFragmentBinding::inflate

    private val homeViewModel: HomeViewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

    private val viewModelMain: ViewModelMain by lazy {
        ViewModelProvider(this)[ViewModelMain::class.java]
    }

    private val filterViewModel: FilterViewModel by lazy {
        ViewModelProvider(this)[FilterViewModel::class.java]
    }

//    companion object {
//        const val NOTIFICATION_ID = 32
//        private const val CHANNEL_ID = "channel_0321"
//        private const val CHANNEL_NAME = "chane231n channel"
//    }

    private val filterBottomSheet = FilterBottomSheet()

    private lateinit var sound: Uri

    override fun setupView(binding: HomeFragmentBinding) {

//        val mStartServiceIntent = Intent(this, AlarmService::class.java)
//        startService(mStartServiceIntent)
//        requireActivity().startService(Intent(context, AlarmService::class.java))

        binding.floatingActionButton.setOnClickListener {
            filterViewModel.openBottomSheet()
//            sendNotification(it)
        }

        filterViewModel.bottomSheet.observe(viewLifecycleOwner) {
            if (it)
                if (!filterBottomSheet.isAdded)
                    filterBottomSheet.show(childFragmentManager, FilterBottomSheet.TAG)
        }
        val droughtListAdapter = DroughtListAdapter()
        showDroughtListRecycler(droughtListAdapter)
        viewModelMain.kbdi.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                droughtListAdapter.submitList(it)
                droughtListAdapter.notifyDataSetChanged()
            }

        }
        viewModelMain.getKbdi()
    }

    private fun showDroughtListRecycler(droughtListAdapter: DroughtListAdapter) {
        val droughtRecyclerView = binding.rvDrought
        droughtRecyclerView.adapter = droughtListAdapter

        droughtListAdapter.submitList(ArrayList<Kbdi>())

        droughtListAdapter.setOnItemClickCallback(object :
            DroughtListAdapter.OnItemClickCallback {
            override fun onItemClicked(kbdi: Kbdi) {
                val array: List<Kbdi>? = viewModelMain.kbdi.value
                array?.filter { it.isActive }?.forEach { data ->
                    data.isActive = false
                }
                array?.single { it.id == kbdi.id }?.let { id ->
                    id.isActive = true
                    Toast.makeText(context, "Set up Alarm", Toast.LENGTH_SHORT).show()
                }

                val kbdiPreference = KbdiPreference(requireContext())

                kbdiPreference.setKbdi(array?.single { it.id == kbdi.id }
                    ?: Kbdi(peatlandCover = PeatlandCover()))

//                binding.tvFieldLabel.text = kbdiPreference.getKbdi().kbdi.toString()
                viewModelMain.changeKbdi(array ?: ArrayList<Kbdi>())

            }
        })
    }

//    fun sendNotification(view: View) {
//
//        val mNotificationManager =
//            activity?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//
//        val attributes = AudioAttributes.Builder()
//            .setUsage(AudioAttributes.USAGE_NOTIFICATION)
//            .build()
//
//
//        val mBuilder = NotificationCompat.Builder(requireActivity(), CHANNEL_ID)
//            .setSmallIcon(R.drawable.ic_baseline_home_24)
//            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_baseline_home_24))
//            .setContentTitle(resources.getString(R.string.curah_hujan))
//            .setContentText(resources.getString(R.string.curah_hujan))
//            .setSubText(resources.getString(R.string.curah_hujan))
//            .setSound(UriUtils.getUriToResource(requireContext(), R.raw.low))
//            .setAutoCancel(true)
//
//        /*
//        Untuk android Oreo ke atas perlu menambahkan notification channel
//        */
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            /* Create or update. */
//
//            val soundAttributes = AudioAttributes.Builder()
//                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
//                .build()
//
//            var notificationId = 1
//            var channelId = "channel_low"
//            var channelName = "low channel"
//
//            val statusAlarm = "Low"
//
//            if (statusAlarm == "Low") {
//                sound = Uri.parse("android.resource://" + context?.packageName + "/" + R.raw.low)
//            } else if(statusAlarm == "Moderate") {
//                notificationId = 2
//                channelId = "channel_moderate"
//                channelName = "moderate channel"
//                sound = Uri.parse("android.resource://" + context?.packageName + "/" + R.raw.moderate)
//            }else if (statusAlarm =="Hight"){
//                notificationId = 3
//                channelId = "channel_hight"
//                channelName = "hight channel"
//                sound = Uri.parse("android.resource://" + context?.packageName + "/" + R.raw.moderate)
//            } else if(statusAlarm =="Extreme"){
//                notificationId = 4
//                channelId = "channel_extreme"
//                channelName = "extreme channel"
//                sound = Uri.parse("android.resource://" + context?.packageName + "/" + R.raw.extreme)
//            }
//
//            val channel = NotificationChannel(
//                channelId,
//                channelName,
//                NotificationManager.IMPORTANCE_DEFAULT
//            )
//            channel.description = channelName
//            channel.setSound(sound, soundAttributes)
//            mBuilder.setChannelId(channelId)
//            mNotificationManager.createNotificationChannel(channel)
//        }
//
//        val notification = mBuilder.build()
//
//        mNotificationManager.notify(NOTIFICATION_ID, notification)
//    }

}