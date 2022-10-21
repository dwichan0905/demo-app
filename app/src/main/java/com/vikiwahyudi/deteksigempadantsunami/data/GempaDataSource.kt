package com.vikiwahyudi.deteksigempadantsunami.data

import androidx.lifecycle.LiveData
import com.vikiwahyudi.deteksigempadantsunami.data.local.entity.DirasakanEntity
import com.vikiwahyudi.deteksigempadantsunami.data.local.entity.MagnitudoEntity
import com.vikiwahyudi.deteksigempadantsunami.data.local.entity.TerkiniEntity
import com.vikiwahyudi.deteksigempadantsunami.data.remote.response.terkini.TerkiniResponse

interface GempaDataSource {
    fun getGempaTerkini(): LiveData<TerkiniEntity>
    fun getGempaDirasakan(): LiveData<List<DirasakanEntity>>
    fun getGempaMagnitudo(): LiveData<List<MagnitudoEntity>>
}