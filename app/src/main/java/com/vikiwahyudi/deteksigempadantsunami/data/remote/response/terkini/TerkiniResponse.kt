package com.vikiwahyudi.deteksigempadantsunami.data.remote.response.terkini

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TerkiniResponse(

    @field:SerializedName("Wilayah")
    val wilayah: String? = null,

    @field:SerializedName("Shakemap")
    val shakemap: String? = null,

    @field:SerializedName("Kedalaman")
    val kedalaman: String? = null,

    @field:SerializedName("Jam")
    val jam: String? = null,

    @field:SerializedName("Coordinates")
    val coordinates: String? = null,

    @field:SerializedName("Prediksi")
    val prediksi: String? = null,

    @field:SerializedName("Tanggal")
    val tanggal: String? = null,

    @field:SerializedName("Bujur")
    val bujur: String? = null,

    @field:SerializedName("Magnitude")
    val magnitude: String? = null,

    @field:SerializedName("Lintang")
    val lintang: String? = null,

    @field:SerializedName("DateTime")
    val dateTime: String? = null
) : Parcelable
