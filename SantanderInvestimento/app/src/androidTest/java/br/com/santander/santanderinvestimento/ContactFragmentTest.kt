package br.com.santander.santanderinvestimentomatches

import android.view.View
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.runner.AndroidJUnit4
import br.com.santander.santanderinvestimento.R
import br.com.santander.santanderinvestimento.dashboard.DashboardActivity
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations

@RunWith(AndroidJUnit4::class)
class ContactFragmentTest {

    @Rule
    @JvmField
    val activity = IntentsTestRule<DashboardActivity>(DashboardActivity::class.java)

    @Before
    fun init() {

    }


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        //activity.getActivity().getSupportFragmentManager().beginTransaction()
    }

    @Test
    fun testComponentsDisplay() {
        Espresso.onView(ViewMatchers.withId(R.id.txtContact))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.contactForm))
                .check(ViewAssertions.matches(ViewMatchers.isCompletelyDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.successForm))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
    @Test
    fun testSwipeToRightViewPager() {
        Espresso.onView(withId(R.id.vpDashboard)).perform(ViewActions.swipeLeft())
    }

    @Test
    fun testSwipeRefresh() {
        Espresso.onView(withId(R.id.swiperefresh)).perform(ViewActions.swipeDown())
    }




}