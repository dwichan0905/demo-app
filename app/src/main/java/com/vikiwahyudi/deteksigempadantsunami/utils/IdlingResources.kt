package com.vikiwahyudi.deteksigempadantsunami.utils

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource

object IdlingResources {
    private val RESOURCES = "GLOBAL"
    private val idling = CountingIdlingResource(RESOURCES)

    fun beginIdle() {
        idling.increment()
    }

    fun endIdle() {
        idling.decrement()
    }

    fun getIdlingResource(): IdlingResource = idling
}