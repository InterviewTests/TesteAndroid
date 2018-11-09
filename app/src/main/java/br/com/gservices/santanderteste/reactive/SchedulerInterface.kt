package br.com.gservices.santanderteste.reactive

import io.reactivex.Scheduler

interface SchedulerInterface {
    fun io(): Scheduler
    fun ui(): Scheduler

}