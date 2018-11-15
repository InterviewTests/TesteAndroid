package com.galdino.testandroid.domain.executor

import io.reactivex.Scheduler

interface UIScheduler {
    fun getScheduler(): Scheduler
}