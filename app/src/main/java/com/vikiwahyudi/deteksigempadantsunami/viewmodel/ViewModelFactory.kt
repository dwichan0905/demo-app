package com.vikiwahyudi.deteksigempadantsunami.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vikiwahyudi.deteksigempadantsunami.data.DataGempaRepository
import com.vikiwahyudi.deteksigempadantsunami.di.Injection
import com.vikiwahyudi.deteksigempadantsunami.ui.dirasakan.DirasakanViewModel
import com.vikiwahyudi.deteksigempadantsunami.ui.home.HomeViewModel
import com.vikiwahyudi.deteksigempadantsunami.ui.magnitude.MagnitudoViewModel

class ViewModelFactory private constructor(private val  mDataGempaRepository: DataGempaRepository): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MagnitudoViewModel::class.java) -> {
                MagnitudoViewModel(mDataGempaRepository) as T
            }
            modelClass.isAssignableFrom(DirasakanViewModel::class.java) -> {
                DirasakanViewModel(mDataGempaRepository) as T
            }
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(mDataGempaRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var instance:  ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }
    }
}