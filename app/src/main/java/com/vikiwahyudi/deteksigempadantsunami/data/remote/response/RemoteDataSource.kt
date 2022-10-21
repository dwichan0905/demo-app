package com.vikiwahyudi.deteksigempadantsunami.data.remote.response

import android.util.Log
import com.vikiwahyudi.deteksigempadantsunami.data.remote.response.dirasakan.DirasakanResponse
import com.vikiwahyudi.deteksigempadantsunami.data.remote.response.dirasakan.InfoGempaDirasakan
import com.vikiwahyudi.deteksigempadantsunami.data.remote.response.magnitudo.InfoGempaMagnitudo
import com.vikiwahyudi.deteksigempadantsunami.data.remote.response.magnitudo.MagnitudoResponse
import com.vikiwahyudi.deteksigempadantsunami.data.remote.response.terkini.InfoGempaTerkini
import com.vikiwahyudi.deteksigempadantsunami.data.remote.response.terkini.TerkiniResponse
import com.vikiwahyudi.deteksigempadantsunami.network.Config
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    fun getGempaTerkini(callback: LoadGempaTerkini) {
        val client = Config.getService().getTerkini()
        client.enqueue(object : Callback<InfoGempaTerkini> {
            override fun onResponse(
                call: Call<InfoGempaTerkini>,
                response: Response<InfoGempaTerkini>
            ) {
                response.body()?.infogempa?.let { callback.onLoadGempaTerkini(it.gempa) }
            }

            override fun onFailure(call: Call<InfoGempaTerkini>, t: Throwable) {
                Log.e("RemoteDataSource", "getGempaTerkini onFailure: ${t.message}")
            }

        })
    }

    fun getGempaDirasakan(callback: LoadGempaDirasakan) {
        val client = Config.getService().getDirasakan()
        client.enqueue(object : Callback<InfoGempaDirasakan> {
            override fun onResponse(
                call: Call<InfoGempaDirasakan>,
                response: Response<InfoGempaDirasakan>
            ) {
                callback.onLoadGempaDirasakan(response.body()?.infogempa?.gempa)
            }

            override fun onFailure(call: Call<InfoGempaDirasakan>, t: Throwable) {
                Log.e("RemoteDataSource", "getGempaDirasakan onFailure : ${t.message}")
            }

        })
    }


    fun getGempaMagnitudo(callback: LoadGempaMagnitudo) {
        val client = Config.getService().getMagnitudo()
        client.enqueue(object : Callback<InfoGempaMagnitudo> {
            override fun onResponse(
                call: Call<InfoGempaMagnitudo>,
                response: Response<InfoGempaMagnitudo>
            ) {
                callback.onLoadGempaMagnitudo(response.body()?.infogempa?.gempa)
            }

            override fun onFailure(call: Call<InfoGempaMagnitudo>, t: Throwable) {
                Log.e("RemoteDataSource", "getGempaMagnitudo onFailure : ${t.message}")
            }

        })
    }

    interface LoadGempaMagnitudo {
        fun onLoadGempaMagnitudo(gempaMagnitudo: List<MagnitudoResponse>?)
    }

    interface LoadGempaDirasakan {
        fun onLoadGempaDirasakan(gempaDirasakan: List<DirasakanResponse>?)
    }


    interface LoadGempaTerkini {
        fun onLoadGempaTerkini(gempaTerkini: TerkiniResponse)
    }

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }
}