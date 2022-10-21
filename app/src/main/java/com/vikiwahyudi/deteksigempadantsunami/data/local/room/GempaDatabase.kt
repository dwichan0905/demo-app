package com.vikiwahyudi.deteksigempadantsunami.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vikiwahyudi.deteksigempadantsunami.data.local.entity.DirasakanEntity
import com.vikiwahyudi.deteksigempadantsunami.data.local.entity.MagnitudoEntity
import com.vikiwahyudi.deteksigempadantsunami.data.local.entity.TerkiniEntity

@Database(
    entities = [TerkiniEntity::class, DirasakanEntity::class, MagnitudoEntity::class],
    version = 1,
    exportSchema = false
)

abstract class GempaDatabase : RoomDatabase() {
    abstract fun gempaDao(): GempaDao

    companion object {
        @Volatile
        private var INSTANCE: GempaDatabase? = null

        fun getInstance(context: Context): GempaDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    GempaDatabase::class.java,
                    "GempaTsunami.db"
                ).build()
            }
    }
}
