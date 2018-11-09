package br.com.gservices.santanderteste

import androidx.test.espresso.IdlingResource

class ElapseTimeResource(waitingTime: Long) : IdlingResource {

    private var startTime: Long = System.currentTimeMillis()
    private var waitingTime: Long = 0
    private var resourceCallback: IdlingResource.ResourceCallback? = null

    init {
        this.waitingTime = waitingTime
    }

    override fun getName(): String {
        return ElapseTimeResource::class.java.name + ":" + waitingTime
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