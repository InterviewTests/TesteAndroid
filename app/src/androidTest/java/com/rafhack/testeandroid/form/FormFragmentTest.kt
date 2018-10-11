package com.rafhack.testeandroid.form

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.rafhack.testeandroid.main.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FormFragmentTest {

    private val activityRule
        @Rule get() = ActivityTestRule<MainActivity>(
                MainActivity::class.java, false, false)

    @Before
    fun setup() {
        activityRule.activity.supportFragmentManager.beginTransaction()
    }

    

}