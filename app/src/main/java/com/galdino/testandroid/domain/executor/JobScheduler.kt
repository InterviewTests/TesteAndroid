package com.galdino.testandroid.domain.executor

import io.reactivex.Scheduler

interface JobScheduler {
    fun getScheduler(): Scheduler
}