package com.vikiwahyudi.deteksigempadantsunami.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vikiwahyudi.deteksigempadantsunami.data.local.LocalDataSource
import com.vikiwahyudi.deteksigempadantsunami.data.local.entity.DirasakanEntity
import com.vikiwahyudi.deteksigempadantsunami.data.local.entity.MagnitudoEntity
import com.vikiwahyudi.deteksigempadantsunami.data.local.entity.TerkiniEntity
import com.vikiwahyudi.deteksigempadantsunami.data.remote.response.RemoteDataSource
import com.vikiwahyudi.deteksigempadantsunami.data.remote.response.dirasakan.DirasakanResponse
import com.vikiwahyudi.deteksigempadantsunami.data.remote.response.magnitudo.MagnitudoResponse
import com.vikiwahyudi.deteksigempadantsunami.data.remote.response.terkini.TerkiniResponse
import com.vikiwahyudi.deteksigempadantsunami.network.ApiResponse
import com.vikiwahyudi.deteksigempadantsunami.network.ApiService
import com.vikiwahyudi.deteksigempadantsunami.network.Config
import com.vikiwahyudi.deteksigempadantsunami.utils.AppExecutors
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class DataGempaRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : GempaDataSource {

    override fun getGempaTerkini(): LiveData<TerkiniEntity> {
        val gempaTerkini = MutableLiveData<TerkiniEntity>()

        remoteDataSource.getGempaTerkini(object : RemoteDataSource.LoadGempaTerkini {
            override fun onLoadGempaTerkini(terkini: TerkiniResponse) {
                val gempa = TerkiniEntity(
                    terkini.wilayah.toString(),
                    terkini.shakemap.toString(),
                    terkini.kedalaman.toString(),
                    terkini.jam.toString(),
                    terkini.coordinates.toString(),
                    terkini.prediksi.toString(),
                    terkini.tanggal.toString(),
                    terkini.bujur.toString(),
                    terkini.magnitude.toString(),
                    terkini.lintang.toString(),
                    terkini.dateTime.toString(),
                )
                insertGempaTerkini(gempa)
                gempaTerkini.postValue(gempa)
            }

        })

        gempaTerkini.postValue(runBlocking {
            GlobalScope.async { localDataSource.getGempaTerkini() }.await()
        })

        return gempaTerkini
    }

    fun getAutoGempaSync(): ApiResponse<TerkiniResponse> {
        val result: ApiResponse<TerkiniResponse>
        val call = Config.getService().getAutoGempaSync().execute()
        result = if (call.isSuccessful) {
            ApiResponse.success(call.body()?.infogempa?.gempa!!)
        } else {
            ApiResponse.error(call.message().toString(), TerkiniResponse())
        }
        return result
    }

    override fun getGempaDirasakan(): LiveData<List<DirasakanEntity>> {
        val gempaDirasakan = MutableLiveData<List<DirasakanEntity>>()

        remoteDataSource.getGempaDirasakan(object : RemoteDataSource.LoadGempaDirasakan {
            override fun onLoadGempaDirasakan(dirasakan: List<DirasakanResponse>?) {
                val dirasakanList = ArrayList<DirasakanEntity>()
                if (dirasakan != null) {
                    for (response in dirasakan) {
                        with(response) {
                            val gempa = DirasakanEntity(
                                wilayah,
                                kedalaman,
                                jam,
                                coordinates,
                                prediksi,
                                tanggal,
                                bujur,
                                magnitude,
                                lintang,
                                dateTime
                            )
                            dirasakanList.add(gempa)
                            insertGempaDirasakan(gempa)
                        }
                    }
                    gempaDirasakan.postValue(dirasakanList)
                }
            }
        })

        gempaDirasakan.postValue(runBlocking {
            GlobalScope.async { localDataSource.getGempaDirasakan() }.await()
        })

        return gempaDirasakan
    }


    override fun getGempaMagnitudo(): LiveData<List<MagnitudoEntity>> {
        val gempaMagnitudo = MutableLiveData<List<MagnitudoEntity>>()

        remoteDataSource.getGempaMagnitudo(object : RemoteDataSource.LoadGempaMagnitudo {
            override fun onLoadGempaMagnitudo(magnitudo: List<MagnitudoResponse>?) {
                val magnitudoList = ArrayList<MagnitudoEntity>()
                if (magnitudo != null) {
                    for (response in magnitudo) {
                        with(response) {
                            val gempa = MagnitudoEntity(
                                wilayah,
                                kedalaman,
                                jam,
                                coordinates,
                                prediksi,
                                tanggal,
                                bujur,
                                magnitude,
                                lintang,
                                dateTime
                            )
                            magnitudoList.add(gempa)
                            insertGempaMagnitudo(gempa)
                        }
                    }
                    gempaMagnitudo.postValue(magnitudoList)
                }
            }
        })
        gempaMagnitudo.postValue(runBlocking {
            GlobalScope.async { localDataSource.getGempaMagnitudo() }.await()
        })

        return gempaMagnitudo
    }


    private fun insertGempaTerkini(gempa: TerkiniEntity) {
        appExecutors.diskIO().execute {
            localDataSource.insertGempaTerkini(gempa)
        }
    }

    private fun insertGempaMagnitudo(gempa: MagnitudoEntity) {
        appExecutors.diskIO().execute {
            localDataSource.insertGempaMagnitudo(gempa)
        }
    }

    private fun insertGempaDirasakan(gempa: DirasakanEntity) {
        appExecutors.diskIO().execute {
            localDataSource.insertGempaDirasakan(gempa)
        }
    }

    companion object {
        @Volatile
        private var instance: DataGempaRepository? = null
        fun getInstance(
            remoteData: RemoteDataSource,
            localDataSource: LocalDataSource,
            appExecutors: AppExecutors
        ): DataGempaRepository =
            instance ?: synchronized(this) {
                instance ?: DataGempaRepository(remoteData, localDataSource, appExecutors)
            }
    }
}