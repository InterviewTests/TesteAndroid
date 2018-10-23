package br.com.santander.santanderinvestimento

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.runner.AndroidJUnit4
import br.com.santander.santanderinvestimento.dashboard.DashboardActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations

@RunWith(AndroidJUnit4::class)
class InvestmentFragmentTest {
    @Rule
    @JvmField
    val activity = IntentsTestRule<DashboardActivity>(DashboardActivity::class.java)

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }


    @Test
    fun testBillingFragmentAlreadyAttached() {
        Espresso.onView(withId(R.id.vpDashboard)).perform(ViewActions.swipeRight())
    }

    @Test
    fun testInvestmentDataDisplayed() {
        Espresso.onView(withId(R.id.vpDashboard)).perform(ViewActions.swipeRight())
        val idlingResource = ElapsedTimeIdlingResource(100L)
        val registry = IdlingRegistry.getInstance()
        registry.register(idlingResource)
        onView(withId(R.id.txHeader)).check(matches(withText(R.string.investiment)))
        onView(withId(R.id.txTitle)).check(matches(withText("Fundos de investimento")))
        onView(withId(R.id.txFundName)).check(matches(withText("Vinci Valorem FI Multimercado")))
        onView(withId(R.id.ivSeparator)).check(matches(isDisplayed()))
        onView(withId(R.id.txWhatsIs)).check(matches(withText("O que é?")))
        /*onView(withId(R.id.txDefinition)).check(matches(withText("O Fundo tem por objetivo proporcionar")))
        onView(withId(R.id.txRiskTitle)).check(matches(not(withText(""))))
        onView(withId(R.id.constraintLayout)).check(matches(not(withText(""))))
        onView(withId(R.id.txInfoTitle)).check(matches(not(withText(""))))
        onView(withId(R.id.txFound)).check(matches(not(withText(""))))
        onView(withId(R.id.txMonthFound)).check(matches(not(withText(""))))
        onView(withId(R.id.txYearFound)).check(matches(not(withText(""))))
        onView(withId(R.id.tx12MonthFound)).check(matches(not(withText(""))))
        onView(withId(R.id.txMonthCDI)).check(matches(not(withText(""))))
        onView(withId(R.id.txYearCDI)).check(matches(not(withText(""))))
        onView(withId(R.id.tx12MonthCDI)).check(matches(not(withText(""))))
        onView(withId(R.id.txCdi)).check(matches(not(withText(""))))
        onView(withId(R.id.txInMonth)).check(matches(not(withText(""))))
        onView(withId(R.id.txInYear)).check(matches(not(withText(""))))
        onView(withId(R.id.tx12Month)).check(matches(not(withText(""))))*/
        onView(withId(R.id.ivShare)).check(matches(isDisplayed()))
     //   onView(withId(R.id.imageView)).check(matches(isDisplayed()))
        onView(withId(R.id.btnInvest)).check(matches(isDisplayed()))
        onView(withId(R.id.rcvInfo)).check(matches(hasMinimumChildCount(1)))
        onView(withId(R.id.rcvDownInfo)).check(matches(hasMinimumChildCount(1)))
        registry.unregister(idlingResource)

    }


}