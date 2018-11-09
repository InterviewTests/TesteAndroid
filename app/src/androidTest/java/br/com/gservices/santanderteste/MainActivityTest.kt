@file:Suppress("DEPRECATION")

package br.com.gservices.santanderteste

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.swipeRight
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.runner.AndroidJUnit4
import br.com.gservices.santanderteste.ui.MainActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations


@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Rule
    @JvmField
    val activity = IntentsTestRule<MainActivity>(MainActivity::class.java)

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testSwiptDashboard() {
        Espresso.onView(withId(R.id.pager)).perform(swipeRight())
    }

    @Test
    fun testRequiredComponents() {
        Espresso.onView(ViewMatchers.withId(R.id.pager))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.tab))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

}