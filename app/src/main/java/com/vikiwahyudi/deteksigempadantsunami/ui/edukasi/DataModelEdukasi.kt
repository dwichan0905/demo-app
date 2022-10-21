package com.vikiwahyudi.deteksigempadantsunami.ui.edukasi

import com.vikiwahyudi.deteksigempadantsunami.utils.Edukasi

data class DataModelEdukasi(
    val edukasi: Edukasi,
    val isExpandable: Boolean = false
)