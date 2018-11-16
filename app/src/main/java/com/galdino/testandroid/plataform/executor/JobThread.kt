package com.galdino.testandroid.plataform.executor

import com.galdino.testandroid.domain.executor.JobScheduler
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class JobThread: JobScheduler {
    override fun getScheduler(): Scheduler = Schedulers.io()
}