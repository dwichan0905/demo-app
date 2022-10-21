package com.vikiwahyudi.deteksigempadantsunami.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vikiwahyudi.deteksigempadantsunami.data.local.entity.DirasakanEntity
import com.vikiwahyudi.deteksigempadantsunami.data.local.entity.MagnitudoEntity
import com.vikiwahyudi.deteksigempadantsunami.data.local.entity.TerkiniEntity

@Dao
interface GempaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGempaTerkini(terkiniEntity: TerkiniEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGempaDirasakan(dirakanEntity: DirasakanEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGempaMagnitude(magnitudoEntity: MagnitudoEntity)

    @Query("SELECT * FROM terkini_entities")
    fun getGempaTerkini(): TerkiniEntity

    @Query("SELECT * FROM dirasakan_entities")
    fun getGempaDirasakan(): List<DirasakanEntity>

    @Query("SELECT * FROM magnitudo_entities")
    fun getGempaMagnitudo(): List<MagnitudoEntity>
}