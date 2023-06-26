package com.example.alarmtest.models

import android.content.Context

internal class KbdiPreference(context: Context) {

    companion object {
        private const val ID = "ID"
        private const val KBDI = "KBDI"
        private const val STATUS = "status"
        private const val DATE = "DATE"
        private const val NAMECOVER = "name"
        private const val ISACTIVE = "false"
    }

    private val preferences = context.getSharedPreferences(ID, Context.MODE_PRIVATE)
    fun setKbdi(value: Kbdi) {
        val editor = preferences.edit()
        editor.putInt(ID, value.id)
        editor.putFloat(KBDI, value.kbdi)
        editor.putString(STATUS, value.status)
        editor.putString(NAMECOVER, value.peatlandCover.name)
        editor.putString(DATE, value.date)
        editor.putBoolean(ISACTIVE, value.isActive)
        editor.apply()
    }

    fun getKbdi(): Kbdi {
        val model = Kbdi(peatlandCover = PeatlandCover())
        model.id = preferences.getInt(ID, 1)
        model.kbdi = preferences.getFloat(KBDI, 1F)
        model.status = preferences.getString(STATUS, "").toString()
        model.date = preferences.getString(DATE, "").toString()
        model.peatlandCover.name = preferences.getString(NAMECOVER, "").toString()
        model.isActive = preferences.getBoolean(ISACTIVE, false)

        return model
    }

}