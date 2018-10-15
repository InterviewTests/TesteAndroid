package com.rafhack.testeandroid.form

import android.support.test.espresso.IdlingResource

class ElapsedTimeIdlingResource(waitingTime: Long) : IdlingResource {

    private var startTime: Long = System.currentTimeMillis()
    private var waitingTime: Long = 0
    private var resourceCallback: IdlingResource.ResourceCallback? = null

    init {
        this.waitingTime = waitingTime
    }

    override fun getName(): String {
        return ElapsedTimeIdlingResource::class.java.name + ":" + waitingTime
    }

    override fun isIdleNow(): Boolean {
        val elapsed = System.currentTimeMillis() - startTime
        val idle = elapsed >= waitingTime
        if (idle) {
            resourceCallback!!.onTransitionToIdle()
        }
        return idle
    }

    override fun registerIdleTransitionCallback(resourceCallback: IdlingResource.ResourceCallback) {
        this.resourceCallback = resourceCallback
    }

}