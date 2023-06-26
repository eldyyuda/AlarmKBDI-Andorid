package com.example.alarmtest

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.compose.ui.input.key.Key.Companion.D
import androidx.lifecycle.coroutineScope
import com.aminography.choosephotohelper.ChoosePhotoHelper
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.alarmtest.models.Item
import com.example.alarmtest.network.IndexApi
import kotlinx.coroutines.launch


class UsedInActivityActivity : AppCompatActivity() {

    private lateinit var autotext: AutoCompleteTextView
    private lateinit var nameTv : TextView
    private lateinit var priceTv : TextView
    private lateinit var descTv : TextView
    private lateinit var btnKirim: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_used_in_activity)
        autotext = findViewById(R.id.taoffers) as AutoCompleteTextView
        btnKirim = findViewById(R.id.btnSend)
        nameTv = findViewById(R.id.tfName)
        priceTv = findViewById(R.id.tfPrice)
        descTv = findViewById(R.id.tfDesc)
        val item = listOf("True", "False")
        val adapter = ArrayAdapter(this, R.layout.list_item, item)
        autotext.setAdapter(adapter)
        sendData()

    }
        private fun sendData() {
        btnKirim.setOnClickListener {
            val name: String = nameTv.text.toString()
            Log.d("Name","${name}")
            val price: Float = priceTv.text.toString().toFloat()
            val desc: String = descTv.text.toString()
            val on_offers : String=  autotext.text.toString()
            var on_offer : Boolean = false
            if(on_offers == "True")
                on_offer = true
            else if(on_offers == "False")
                on_offer = false
            val send = Item(name, price, desc,on_offer)
            Log.d("send","${send}")
            lifecycle.coroutineScope.launch {
                try {
//
                } catch (e: Exception) {
                    Log.d("eror","${e}")
                }

            }

        }
    }
}