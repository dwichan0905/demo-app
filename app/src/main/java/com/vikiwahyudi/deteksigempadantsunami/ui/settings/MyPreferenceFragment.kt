package com.vikiwahyudi.deteksigempadantsunami.ui.settings

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.CheckBoxPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.work.Data
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.vikiwahyudi.deteksigempadantsunami.R
import com.vikiwahyudi.deteksigempadantsunami.utils.NOTIFICATION_CHANNEL_ID
import java.util.concurrent.TimeUnit

class MyPreferenceFragment : PreferenceFragmentCompat() {
    private lateinit var prefNotification: CheckBoxPreference

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.root_preferences)
        initComponents()
    }

    private fun initComponents() {
        prefNotification = findPreference<CheckBoxPreference>(getString(R.string.pref_key_notify)) as CheckBoxPreference
        prefNotification.onPreferenceChangeListener = onNotificationPreferenceChange()
    }

    @SuppressLint("InvalidPeriodicWorkRequestInterval")
    private fun onNotificationPreferenceChange(): Preference.OnPreferenceChangeListener =
        Preference.OnPreferenceChangeListener { _, newValue ->
            val workManager = WorkManager.getInstance(requireContext())
            val periodicWorkRequest = PeriodicWorkRequest.Builder(
                NotificationTerkiniWorker::class.java,
                3,
                TimeUnit.MINUTES
            ).build()

            if (newValue.equals(true)) {
                workManager.enqueue(periodicWorkRequest)
            } else {
                workManager.cancelAllWork()
            }
            true
        }
}