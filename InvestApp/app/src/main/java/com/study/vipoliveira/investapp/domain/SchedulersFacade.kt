package com.study.vipoliveira.investapp.domain

import javax.inject.Inject

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Provides various threading schedulers.
 */

class SchedulersFacade @Inject
constructor() {

    /**
     * IO thread pool scheduler
     */
    fun io(): Scheduler {
        return Schedulers.io()
    }

    /**
     * Computation thread pool scheduler
     */
    fun computation(): Scheduler {
        return Schedulers.computation()
    }

    /**
     * Main Thread scheduler
     */
    fun ui(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}