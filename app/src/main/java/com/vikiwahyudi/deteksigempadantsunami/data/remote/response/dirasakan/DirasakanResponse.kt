package com.vikiwahyudi.deteksigempadantsunami.data.remote.response.dirasakan

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class DirasakanResponse(

    @field:SerializedName("Wilayah")
    val wilayah: String,

    @field:SerializedName("Kedalaman")
    val kedalaman: String,

    @field:SerializedName("Jam")
    val jam: String,

    @field:SerializedName("Coordinates")
    val coordinates: String,

    @field:SerializedName("Prediksi")
    val prediksi: String,

    @field:SerializedName("Tanggal")
    val tanggal: String,

    @field:SerializedName("Bujur")
    val bujur: String,

    @field:SerializedName("Magnitude")
    val magnitude: String,

    @field:SerializedName("Lintang")
    val lintang: String,

    @field:SerializedName("DateTime")
    val dateTime: String
) : Parcelable
