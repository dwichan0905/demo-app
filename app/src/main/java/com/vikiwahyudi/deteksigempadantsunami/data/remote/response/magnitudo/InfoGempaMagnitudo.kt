package com.vikiwahyudi.deteksigempadantsunami.data.remote.response.magnitudo

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class InfoGempaMagnitudo(
    @field:SerializedName("Infogempa")
    val infogempa: GempaMagnitudo
) : Parcelable