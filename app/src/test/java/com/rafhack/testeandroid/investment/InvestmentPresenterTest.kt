package com.rafhack.testeandroid.investment

import com.rafhack.testeandroid.data.domain.InvestmentInteractor
import com.rafhack.testeandroid.data.entities.investment.Investment
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class InvestmentPresenterTest {

    @Mock
    private val view: InvestmentContract.View? = null
    @Mock
    private val interactor: InvestmentInteractor? = null
    private lateinit var presenter: InvestmentPresenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        presenter = InvestmentPresenter(view!!)
        presenter.interactor = interactor!!
    }

    @Test
    fun loadInvestmentSuccess() {
        val response = Mockito.mock(Investment::class.java)
        Mockito.doReturn(Single.just(response)).`when`(interactor)?.getInvestment()
        presenter.loadInvestments()
        Mockito.verify(view!!).showInvestmentInfo(response)
    }

    @Test
    fun loadCellsError() {
        val response = Exception("")
        val single: Single<Throwable> = Single.error(response)
        Mockito.doReturn(single).`when`(interactor)?.getInvestment()
        presenter.loadInvestments()
        Mockito.verify(view!!).showErrorMessage(response.message!!)
    }

}