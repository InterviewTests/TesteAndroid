@file:Suppress("DEPRECATION")

package br.com.gservices.santanderteste

import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.runner.AndroidJUnit4
import br.com.gservices.santanderteste.ui.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations

@RunWith(AndroidJUnit4::class)
class ContactsFragmentTest {

    @Rule
    @JvmField
    val activity = IntentsTestRule<MainActivity>(MainActivity::class.java)

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testComponentsContactDisplay() {
        val idlingResource = ElapseTimeResource(4000L)
        val registry = IdlingRegistry.getInstance()
        registry.register(idlingResource)
        Espresso.onView(ViewMatchers.withId(R.id.txtContact))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.contactForm))
            .check(ViewAssertions.matches(ViewMatchers.isCompletelyDisplayed()))

        registry.unregister(idlingResource)
    }
}