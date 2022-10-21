package com.vikiwahyudi.deteksigempadantsunami.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(
    tableName = "dirasakan_entities",
    indices = arrayOf(Index("dateTime", unique = true))
)
data class DirasakanEntity(
    @ColumnInfo(name = "wilayah")
    val wilayah: String,

    @ColumnInfo(name = "kedalaman")
    val kedalaman: String,

    @ColumnInfo(name = "jam")
    val jam: String,

    @ColumnInfo(name = "coordinates")
    val coordinates: String,

    @ColumnInfo(name = "prediksi")
    val prediksi: String,

    @ColumnInfo(name = "tanggal")
    val tanggal: String,

    @ColumnInfo(name = "bujur")
    val bujur: String,

    @ColumnInfo(name = "magnitude")
    val magnitude: String,

    @ColumnInfo(name = "lintang")
    val lintang: String,

    @PrimaryKey
    @ColumnInfo(name = "dateTime")
    val dateTime: String
) : Parcelable