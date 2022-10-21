package com.vikiwahyudi.deteksigempadantsunami.data.remote.response.terkini

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class GempaTerkini(
    @field:SerializedName("gempa")
    val gempa: TerkiniResponse
) : Parcelable
