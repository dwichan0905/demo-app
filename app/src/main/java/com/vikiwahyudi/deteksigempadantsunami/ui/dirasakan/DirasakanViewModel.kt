package com.vikiwahyudi.deteksigempadantsunami.ui.dirasakan

import androidx.lifecycle.ViewModel
import com.vikiwahyudi.deteksigempadantsunami.data.DataGempaRepository

class DirasakanViewModel(private val dataGempaRepository: DataGempaRepository) : ViewModel() {
    fun getGempaDirasakan() = dataGempaRepository.getGempaDirasakan()
}