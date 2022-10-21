package com.vikiwahyudi.deteksigempadantsunami.network

import com.vikiwahyudi.deteksigempadantsunami.data.remote.response.dirasakan.InfoGempaDirasakan
import com.vikiwahyudi.deteksigempadantsunami.data.remote.response.magnitudo.InfoGempaMagnitudo
import com.vikiwahyudi.deteksigempadantsunami.data.remote.response.terkini.InfoGempaTerkini
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("gempadirasakan")
    fun getDirasakan(): Call<InfoGempaDirasakan>

    @GET("gempaterkini")
    fun getMagnitudo(): Call<InfoGempaMagnitudo>

    @GET("gempaterbaru")
    fun getTerkini(): Call<InfoGempaTerkini>

    @GET("gempaterbaru")
    fun getAutoGempaSync(): Call<InfoGempaTerkini>

}