package com.vikiwahyudi.deteksigempadantsunami.ui.home

import androidx.lifecycle.ViewModel
import com.vikiwahyudi.deteksigempadantsunami.data.DataGempaRepository

class HomeViewModel(private val dataGempaRepository: DataGempaRepository) : ViewModel() {
    fun getGempaTerkini() = dataGempaRepository.getGempaTerkini()
}
