package com.vikiwahyudi.deteksigempadantsunami.ui.settings

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.vikiwahyudi.deteksigempadantsunami.R
import com.vikiwahyudi.deteksigempadantsunami.data.DataGempaRepository
import com.vikiwahyudi.deteksigempadantsunami.data.remote.response.terkini.TerkiniResponse
import com.vikiwahyudi.deteksigempadantsunami.network.StatusResponse
import com.vikiwahyudi.deteksigempadantsunami.ui.HomeActivity
import com.vikiwahyudi.deteksigempadantsunami.utils.CHANNEL_NAME
import com.vikiwahyudi.deteksigempadantsunami.utils.IdlingResources
import com.vikiwahyudi.deteksigempadantsunami.utils.NOTIFICATION_CHANNEL_ID


class NotificationTerkiniWorker(
    ctx: Context,
    params: WorkerParameters,
    private val networkRepository: DataGempaRepository,
    private val notificationPreference: NotificationPreference
) : Worker(ctx, params) {

    private fun getPendingIntent(gempaItem: TerkiniResponse): PendingIntent? {
        val intent = Intent(applicationContext, HomeActivity::class.java).apply {
            putExtra("data", gempaItem)
        }
        return TaskStackBuilder.create(applicationContext).run {
            addNextIntentWithParentStack(intent)
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }
    }

    override fun doWork(): Result {
        IdlingResources.beginIdle()
        val api = networkRepository.getAutoGempaSync()
        val local = notificationPreference.initComponents().getLastInfo()

        when (api.status) {
            StatusResponse.SUCCESS -> {
                if (api.body?.tanggal != local.tanggal && local.tanggal != null && api.body?.tanggal != null) {
                    showNotification(api.body)
                    notificationPreference.initComponents().setLastInfo(api.body)
                }
                IdlingResources.endIdle()
                return Result.success()
            }
            StatusResponse.ERROR -> {
                Log.e("NotificationWorker", api.message.toString())
                IdlingResources.endIdle()
                return Result.retry()
            }
            StatusResponse.EMPTY -> {
                Log.d("NotificationWorker", "Empty data received")
                IdlingResources.endIdle()
                return Result.success()
            }
            else -> {
                Log.d("NotificationWorker", "Unknown Reason")
                IdlingResources.endIdle()
                return Result.retry()
            }

        }
    }

    private fun showNotification(item: TerkiniResponse) {
        val intent = getPendingIntent(item)
        val notificationStyle = NotificationCompat.BigTextStyle()
            .bigText(
                applicationContext.getString(
                    R.string.notify_content,
                    item.magnitude,
                    item.tanggal,
                    item.jam,
                    item.kedalaman,
                    item.wilayah,
                    item.prediksi
                )
            ).setBigContentTitle(applicationContext.getString(R.string.notify_title, item.tanggal))
        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification = NotificationCompat.Builder(applicationContext, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notifications)
            .setContentTitle(applicationContext.getString(R.string.notify_title, item.tanggal))
            .setContentText(
                applicationContext.getString(
                    R.string.notify_content,
                    item.magnitude,
                    item.tanggal,
                    item.jam,
                    item.kedalaman,
                    item.wilayah,
                    item.prediksi
                )
            )
            .setContentIntent(intent)
            .setStyle(notificationStyle)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH
            )
            notification.setChannelId(NOTIFICATION_CHANNEL_ID)
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(1, notification.build())
    }
}