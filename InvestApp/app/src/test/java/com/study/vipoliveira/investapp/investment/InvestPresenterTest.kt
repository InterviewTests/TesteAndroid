package com.study.vipoliveira.investapp.util.contact

import com.study.vipoliveira.investapp.data.network.investment.entities.InvestResponse
import com.study.vipoliveira.investapp.domain.InvestDomain
import com.study.vipoliveira.investapp.domain.SchedulersFacade
import com.study.vipoliveira.investapp.ui.investment.InvestPresenter
import com.study.vipoliveira.investapp.ui.investment.InvestmentContract
import com.study.vipoliveira.investapp.util.MockUtils
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.*
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnit
import java.util.concurrent.TimeUnit

class InvestPresenterTest {
    @JvmField @Rule var mockitoRule = MockitoJUnit.rule()

    lateinit var investPresenter: InvestmentContract.Presenter

    @Mock
    private lateinit var investView: InvestmentContract.View

    @Mock
    private lateinit var domain: InvestDomain

    @Mock
    private var compositeDisposable = CompositeDisposable()

    @Mock
    private lateinit var investResponse: InvestResponse

    @Mock
    private lateinit var schedulersFacade: SchedulersFacade

    private val testScheduler = TestScheduler()


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        investPresenter = InvestPresenter(domain, schedulersFacade, compositeDisposable)
        investPresenter.attach(investView)
    }

    @Test
    fun checkIfShowProgressOnLoadContactForm() {

        val single = Single.just(investResponse)
        Mockito.`when`(schedulersFacade.io()).thenReturn(testScheduler)
        Mockito.`when`(schedulersFacade.ui()).thenReturn(testScheduler)
        Mockito.`when`(domain.requestInvestment()).thenReturn(single)

        investPresenter.getInvestments()
        verify(investView, times(1))!!.displayLoadingUI()
    }


    @Test
    fun checkIfContactFormIsBeenCreated() {

        val single = Single.just(investResponse)
        Mockito.`when`(schedulersFacade.io()).thenReturn(testScheduler)
        Mockito.`when`(schedulersFacade.ui()).thenReturn(testScheduler)
        Mockito.`when`(domain.requestInvestment()).thenReturn(single)

        investPresenter.getInvestments()
        testScheduler.advanceTimeBy(1, TimeUnit.SECONDS)

        verify(investView, times(1))!!.updateInvestScreen(investResponse.screen)
    }

    @Test
    fun checkIfDisplayErrorIsShowingWhenReceiveError() {
        Mockito.`when`(schedulersFacade.io()).thenReturn(testScheduler)
        Mockito.`when`(schedulersFacade.ui()).thenReturn(testScheduler)
        Mockito.`when`(domain.requestInvestment()).thenReturn(Single.error(MockUtils.getException()))

        investPresenter.getInvestments()
        testScheduler.advanceTimeBy(1, TimeUnit.SECONDS)

        verify(investView, times(1))!!.displayError("Exception")


    }
}