@file:Suppress("DEPRECATION")

package br.com.gservices.santanderteste

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.runner.AndroidJUnit4
import br.com.gservices.santanderteste.ui.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations

@RunWith(AndroidJUnit4::class)
class InvestmentsFragmentTest {
    @Rule
    @JvmField
    val activity = IntentsTestRule<MainActivity>(MainActivity::class.java)

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testSwipeViewPager() {
        Espresso.onView(withId(R.id.pager)).perform(ViewActions.swipeRight())
    }

    @Test
    fun testInvestmentDataDisplayed() {
        Espresso.onView(withId(R.id.pager)).perform(ViewActions.swipeRight())
        val idlingResource = ElapseTimeResource(100L)
        val registry = IdlingRegistry.getInstance()
        registry.register(idlingResource)
        onView(withId(R.id.txHeader)).check(matches(withText(R.string.investiment)))
        onView(withId(R.id.txTitle)).check(matches(withText("Fundos de investimento")))
        onView(withId(R.id.txFundName)).check(matches(withText("Vinci Valorem FI Multimercado")))
        onView(withId(R.id.ivSeparator)).check(matches(isDisplayed()))
        onView(withId(R.id.txWhatsIs)).check(matches(withText("O que é?")))
        onView(withId(R.id.ivShare)).check(matches(isDisplayed()))
        onView(withId(R.id.rcvInfo)).check(matches(hasMinimumChildCount(1)))
        onView(withId(R.id.rcvDownInfo)).check(matches(hasMinimumChildCount(1)))
        registry.unregister(idlingResource)
    }
}