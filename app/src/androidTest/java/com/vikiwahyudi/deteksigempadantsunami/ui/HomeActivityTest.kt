package com.vikiwahyudi.deteksigempadantsunami.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.vikiwahyudi.deteksigempadantsunami.R
import com.vikiwahyudi.deteksigempadantsunami.ui.detailgempa.DetailGempaActivity
import com.vikiwahyudi.deteksigempadantsunami.utils.EdukasiData
import com.vikiwahyudi.deteksigempadantsunami.utils.EspressoIdlingResource
import org.hamcrest.CoreMatchers.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    @Before
    fun setUp() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

//    @Test
//    fun loadTerbaru() {
//        onView(withId(R.id.detail_gempa)).check(matches(isDisplayed()))
//
//        onView(withId(R.id.mapView)).check(matches(isDisplayed()))
//        onView(withId(R.id.tv_tanggal)).check(matches(not(withText(""))))
//        onView(withId(R.id.tv_jam)).check(matches(not(withText(""))))
//        onView(withId(R.id.tv_magnitude)).check(matches(not(withText(""))))
//        onView(withId(R.id.tv_depth)).check(matches(not(withText(""))))
//        onView(withId(R.id.tv_wilayah)).check(matches(not(withText(""))))
//        onView(withId(R.id.tv_potensi_or_dirasakan)).check(matches(not(withText(""))))
//    }

//    @Test
//    fun loadMagnitudo() {
//        onView(withId(R.id.navigation_gempa_terkini)).perform(click())
//        onView(withId(R.id.fragment_magnitudo)).check(matches(isDisplayed()))
//        onView(withId(R.id.rv_gempa)).perform(
//            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(GEMPA_COUNT)
//        )
//    }
//
//    @Test
//    fun loadDirasakan() {
//        onView(withId(R.id.navigation_gempa_dirasakan)).perform(click())
//        onView(withId(R.id.fragment_dirasakan)).check(matches(isDisplayed()))
//        onView(withId(R.id.rv_gempa)).perform(
//            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(GEMPA_COUNT)
//        )
//    }

    @Test
    fun loadEdukasi() {
        onView(withId(R.id.navigation_edukasi)).perform(click())
        onView(withId(R.id.fragment_edukasi)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_edukasi)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(EDUKASI_COUNT)
        )
        onView(withId(R.id.txt_item_edukasi)).check(matches(not(withText(""))))
    }


//    @Test
//    fun loadSettings() {
//        onView(withId(R.id.navigation_settings)).perform(click())
//        onView(withId(R.id.fragment_settings)).check(matches(isDisplayed()))
//    }
//
//    //        onView(withId(R.id.checkbox)).check(matches(isNotChecked())).perform(scrollTo(), click());
//    //        onView(withId(R.id.checkbox)).check(matches(isChecked())).perform(scrollTo(), click());
//
//
//    @Test
//    fun loadDataMagnitudo() {
//        Intents.init()
//        onView(withId(R.id.navigation_gempa_terkini)).perform(click())
//        onView(withId(R.id.rv_gempa)).perform(
//            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
//                0,
//                click()
//            )
//        )
//        intended(hasComponent(DetailGempaActivity::class.java.name))
//        Intents.release()
//
//        onView(withId(R.id.mapView)).check(matches(isDisplayed()))
//        onView(withId(R.id.tv_tanggal)).check(matches(not(withText(""))))
//        onView(withId(R.id.tv_jam)).check(matches(not(withText(""))))
//        onView(withId(R.id.tv_magnitude)).check(matches(not(withText(""))))
//        onView(withId(R.id.tv_depth)).check(matches(not(withText(""))))
//        onView(withId(R.id.tv_wilayah)).check(matches(not(withText(""))))
//        onView(withId(R.id.tv_potensi_or_dirasakan)).check(matches(not(withText(""))))
//    }
//
//    @Test
//    fun loadDataDirasakan() {
//        Intents.init()
//        onView(withId(R.id.navigation_gempa_dirasakan)).perform(click())
//        onView(withId(R.id.rv_gempa)).perform(
//            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
//                0,
//                click()
//            )
//        )
//
//        intended(hasComponent(DetailGempaActivity::class.java.name))
//        Intents.release()
//
//        onView(withId(R.id.mapView)).check(matches(isDisplayed()))
//        onView(withId(R.id.tv_tanggal)).check(matches(not(withText(""))))
//        onView(withId(R.id.tv_jam)).check(matches(not(withText(""))))
//        onView(withId(R.id.tv_magnitude)).check(matches(not(withText(""))))
//        onView(withId(R.id.tv_depth)).check(matches(not(withText(""))))
//        onView(withId(R.id.tv_wilayah)).check(matches(not(withText(""))))
//        onView(withId(R.id.tv_potensi_or_dirasakan)).check(matches(not(withText(""))))
//    }
//
//    @Test
//    fun loadDataEdukasi() {
//        onView(withId(R.id.navigation_edukasi)).perform(click())
//        onView(withId(R.id.rv_edukasi)).perform(
//            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
//                0,
//                click()
//            )
//        )
////        onView(withParentIndex(withId(R.id.txt_item_edukasi), 2)).perform(click());
//        onView(withId(R.id.linear_layout)).check(matches(not(withText(""))))
//    }
//

    companion object {
        private const val GEMPA_COUNT = 15
        private const val EDUKASI_COUNT = 3
    }


}