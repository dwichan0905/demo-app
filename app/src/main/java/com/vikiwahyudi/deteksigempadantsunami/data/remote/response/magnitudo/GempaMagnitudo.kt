package com.vikiwahyudi.deteksigempadantsunami.data.remote.response.magnitudo

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GempaMagnitudo(
    @field:SerializedName("gempa")
    val gempa: List<MagnitudoResponse>
) : Parcelable