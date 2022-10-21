package com.vikiwahyudi.deteksigempadantsunami.ui.settings

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.vikiwahyudi.deteksigempadantsunami.R
import com.vikiwahyudi.deteksigempadantsunami.data.remote.response.terkini.TerkiniResponse

class NotificationPreference constructor(private val application: Application) {

    private val wilayah: String = application.getString(R.string.pref_notify_last_wilayah)
    private val shakemap: String = application.getString(R.string.pref_notify_last_shakemap)
    private val kedalaman: String = application.getString(R.string.pref_notify_last_kedalaman)
    private val jam: String = application.getString(R.string.pref_notify_last_jam)
    private val coordinates: String = application.getString(R.string.pref_notify_last_coordinates)
    private val prediksi: String = application.getString(R.string.pref_notify_last_prediksi)
    private val tanggal: String = application.getString(R.string.pref_notify_last_tanggal)
    private val bujur: String = application.getString(R.string.pref_notify_last_bujur)
    private val magnitude: String = application.getString(R.string.pref_notify_last_magnitude)
    private val lintang: String = application.getString(R.string.pref_notify_last_lintang)
    private val dateTime: String = application.getString(R.string.pref_notify_last_datetime)



    private lateinit var preference: SharedPreferences

    fun initComponents(): NotificationPreference {
        preference = application.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        return this
    }

    fun setLastInfo(item: TerkiniResponse) {
        preference.edit().apply {
            putString(tanggal, item.tanggal)
            putString(jam, item.jam)
            putString(dateTime, item.dateTime)
            putString(kedalaman, item.kedalaman)
            putString(lintang, item.lintang)
            putString(bujur, item.bujur)
            putString(wilayah, item.wilayah)
            putString(coordinates, item.coordinates)
            putString(prediksi, item.prediksi)
            putString(magnitude, item.magnitude)
            putString(shakemap, item.shakemap)
            apply()
        }
    }

    fun getLastInfo(): TerkiniResponse =
        TerkiniResponse(
            tanggal = preference.getString(tanggal, "01 Jan 1970"),
            jam = preference.getString(jam, "23:59:59 WIX"),
            dateTime = preference.getString(dateTime, "01 Jan 1970T23:59:59+00.00"),
            coordinates = preference.getString(coordinates, "0.0,0.0"),
            magnitude = preference.getString(magnitude, "0.0"),
            bujur = preference.getString(bujur, "0.0"),
            lintang = preference.getString(lintang, "0.0"),
            kedalaman = preference.getString(kedalaman, "0 km"),
            shakemap = preference.getString(shakemap, "0.mmi.jpg"),
            wilayah = preference.getString(wilayah, "0 km BaratDaya LEMBAH-UNK"),
            prediksi = preference.getString(prediksi, "Tidak Berpotensi Tsunami")
        )

    companion object {
        private const val PREFERENCE_NAME = "last_quake_preferences"
    }


}