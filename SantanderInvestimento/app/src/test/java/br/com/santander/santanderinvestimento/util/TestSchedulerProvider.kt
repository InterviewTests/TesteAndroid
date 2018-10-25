package br.com.santander.santanderinvestimento.util


import br.com.santander.santanderinvestimento.util.rx.SchedulerProvider
import io.reactivex.schedulers.Schedulers

class TestSchedulerProvider : SchedulerProvider {
    override fun io() = Schedulers.trampoline()

    override fun ui() = Schedulers.trampoline()

}