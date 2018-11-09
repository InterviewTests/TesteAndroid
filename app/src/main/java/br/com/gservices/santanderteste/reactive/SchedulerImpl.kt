package br.com.gservices.santanderteste.reactive

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SchedulerImpl : SchedulerInterface {
    override fun io() = Schedulers.io()
    override fun ui() = AndroidSchedulers.mainThread()
}