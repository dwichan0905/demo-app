package com.vikiwahyudi.deteksigempadantsunami.data.local

import com.vikiwahyudi.deteksigempadantsunami.data.local.entity.DirasakanEntity
import com.vikiwahyudi.deteksigempadantsunami.data.local.entity.MagnitudoEntity
import com.vikiwahyudi.deteksigempadantsunami.data.local.entity.TerkiniEntity
import com.vikiwahyudi.deteksigempadantsunami.data.local.room.GempaDao

class LocalDataSource(private val mGempaDao: GempaDao) {
    fun getGempaTerkini(): TerkiniEntity = mGempaDao.getGempaTerkini()

    fun getGempaDirasakan(): List<DirasakanEntity> = mGempaDao.getGempaDirasakan()

    fun getGempaMagnitudo(): List<MagnitudoEntity> = mGempaDao.getGempaMagnitudo()

    fun insertGempaTerkini(terkiniEntity: TerkiniEntity) {
        mGempaDao.insertGempaTerkini(terkiniEntity)
    }

    fun insertGempaDirasakan(dirasakanEntity: DirasakanEntity) {
        mGempaDao.insertGempaDirasakan(dirasakanEntity)
    }

    fun insertGempaMagnitudo(magnitudoEntity: MagnitudoEntity) {
        mGempaDao.insertGempaMagnitude(magnitudoEntity)
    }

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(gempaDao: GempaDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(gempaDao)
    }
}