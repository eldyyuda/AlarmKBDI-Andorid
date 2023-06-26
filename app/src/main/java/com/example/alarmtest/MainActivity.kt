package com.example.alarmtest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import com.example.alarmtest.databinding.ActivityMainBinding
import com.example.alarmtest.service.AlarmService
import com.example.alarmtest.ui.home.ViewModelMain

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var alarmService: AlarmService

    private val viewModel: ViewModelMain by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[ViewModelMain::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val mStartServiceIntent = Intent(this, AlarmService::class.java)
        startService(mStartServiceIntent)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.myNavHostFragment) as NavHostFragment
        val navController = navHostFragment.navController

        val bottomNavigation = binding.navView

        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.homeFragment
        ).build()
//        lifecycle.coroutineScope.launch {
//            try {
//                val apiDeferred = IndexApi.retrofitService.getDataAsync()
//                val result = apiDeferred.await()
//                val kbdi = result.data[0].kbdi
//                val date = result.data[1].date
//                val status= result.data[2].status
//                val nameCover = result.data[3].peatlandCover.name
//                Log.d("get","coba get data")
//
//            } catch (e: Exception) {
//                Log.d("eror","${e}")
//            }
//
//        }

    }

}