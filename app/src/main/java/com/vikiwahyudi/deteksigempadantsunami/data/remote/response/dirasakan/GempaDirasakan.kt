package com.vikiwahyudi.deteksigempadantsunami.data.remote.response.dirasakan

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GempaDirasakan(
    @field:SerializedName("gempa")
    val gempa: List<DirasakanResponse>
) : Parcelable