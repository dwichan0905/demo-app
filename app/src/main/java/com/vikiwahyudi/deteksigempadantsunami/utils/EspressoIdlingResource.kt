package com.vikiwahyudi.deteksigempadantsunami.utils

import androidx.test.espresso.idling.CountingIdlingResource

object EspressoIdlingResource {
    private const val resource: String = "GLOBAL"
    val idlingResource = CountingIdlingResource(resource)

    fun increment() {
        idlingResource.increment()
    }

    fun decrement() {
        idlingResource.decrement()
    }
}