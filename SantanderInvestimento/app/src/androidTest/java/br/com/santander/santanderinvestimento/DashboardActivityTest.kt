package br.com.santander.santanderinvestimento

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.swipeRight
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.runner.AndroidJUnit4
import br.com.santander.santanderinvestimento.dashboard.DashboardActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations


@RunWith(AndroidJUnit4::class)
class DashboardActivityTest {
    @Rule
    @JvmField
    val activity = IntentsTestRule<DashboardActivity>(DashboardActivity::class.java)

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testBillingFragmentAlreadyAttached() {
        Espresso.onView(withId(R.id.vpDashboard)).perform(swipeRight())
    }

    @Test
    fun testHomeActivityRequiredComponents() {
        Espresso.onView(ViewMatchers.withId(R.id.vpDashboard))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.tabDashboard))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }


}