package com.vikiwahyudi.deteksigempadantsunami.ui.magnitude

import androidx.lifecycle.ViewModel
import com.vikiwahyudi.deteksigempadantsunami.data.DataGempaRepository

class MagnitudoViewModel(private val dataGempaRepository: DataGempaRepository) : ViewModel() {
    fun getGempaMagnitudo() = dataGempaRepository.getGempaMagnitudo()
}