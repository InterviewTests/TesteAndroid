package com.rafhack.testeandroid.form

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.rafhack.testeandroid.R
import com.rafhack.testeandroid.main.MainActivity
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Suppress("MemberVisibilityCanBePrivate")
    @Rule
    @JvmField
    val activityRule = ActivityTestRule<MainActivity>(
            MainActivity::class.java, false, false)
    private val waitingTime = 100L

    @Before
    fun setup() {
        activityRule.launchActivity(Intent(InstrumentationRegistry.getTargetContext(), MainActivity::class.java))
    }

    @Test
    fun testDinamycFieldsDisplayed() {
        val idlingResource = ElapsedTimeIdlingResource(waitingTime)
        val registry = IdlingRegistry.getInstance()
        registry.register(idlingResource)

        onView(withId(R.id.fragment_form_lin_content)).check(matches(hasMinimumChildCount(1)))

        registry.unregister(idlingResource)
    }

    @Test
    fun testInvestmentDataDisplayed() {
        val idlingResource = ElapsedTimeIdlingResource(waitingTime)
        val registry = IdlingRegistry.getInstance()
        registry.register(idlingResource)

        onView(withId(R.id.fragment_investment_tvw_title)).check(matches(not(withText(""))))
        onView(withId(R.id.fragment_investment_tvw_fund_name)).check(matches(not(withText(""))))
        onView(withId(R.id.fragment_investment_tvw_what_is)).check(matches(not(withText(""))))
        onView(withId(R.id.fragment_investment_tvw_definition)).check(matches(not(withText(""))))
        onView(withId(R.id.fragment_investment_tvw_risk_title)).check(matches(not(withText(""))))
        onView(withId(R.id.investment_more_info_tvw_month_fund)).check(matches(not(withText(""))))
        onView(withId(R.id.investment_more_info_tvw_year_fund)).check(matches(not(withText(""))))
        onView(withId(R.id.investment_more_info_tvw_12_month_fund)).check(matches(not(withText(""))))
        onView(withId(R.id.investment_more_info_tvw_month_di)).check(matches(not(withText(""))))
        onView(withId(R.id.investment_more_info_tvw_year_di)).check(matches(not(withText(""))))
        onView(withId(R.id.investment_more_info_tvw_12_month_di)).check(matches(not(withText(""))))
        onView(withId(R.id.fragment_investment_rcv_info)).check(matches(hasMinimumChildCount(1)))
        onView(withId(R.id.fragment_investment_rcv_down_info)).check(matches(hasMinimumChildCount(1)))


        registry.unregister(idlingResource)
    }


}