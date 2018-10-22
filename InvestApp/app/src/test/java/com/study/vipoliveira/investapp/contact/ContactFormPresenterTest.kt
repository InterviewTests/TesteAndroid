package com.study.vipoliveira.investapp.util.contact

import com.study.vipoliveira.investapp.data.network.contact.entities.ContactFormResponse
import com.study.vipoliveira.investapp.domain.ContactDomain
import com.study.vipoliveira.investapp.domain.SchedulersFacade
import com.study.vipoliveira.investapp.ui.contact.ContactFormContract
import com.study.vipoliveira.investapp.ui.contact.ContactFormPresenter
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

class ContactFormPresenterTest {
    @JvmField @Rule var mockitoRule = MockitoJUnit.rule()

    lateinit var contactPresenter: ContactFormContract.Presenter

    @Mock
    private lateinit var contactView: ContactFormContract.View

    @Mock
    private lateinit var domain: ContactDomain

    @Mock
    private var compositeDisposable = CompositeDisposable()

    @Mock
    private lateinit var contactFormResponse: ContactFormResponse

    @Mock
    private lateinit var schedulersFacade: SchedulersFacade

    private val testScheduler = TestScheduler()


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        contactPresenter = ContactFormPresenter(domain, schedulersFacade, compositeDisposable)
        contactPresenter.attach(contactView)
    }

    @Test
    fun checkIfShowProgressOnLoadContactForm() {

        val single = Single.just(contactFormResponse)
        Mockito.`when`(schedulersFacade.io()).thenReturn(testScheduler)
        Mockito.`when`(schedulersFacade.ui()).thenReturn(testScheduler)
        Mockito.`when`(domain.requestContactForm()).thenReturn(single)

        contactPresenter.getContactForm()
        verify(contactView, times(1))!!.displayLoadingUI()
    }


    @Test
    fun checkIfContactFormIsBeenCreated() {

        val single = Single.just(contactFormResponse)
        Mockito.`when`(schedulersFacade.io()).thenReturn(testScheduler)
        Mockito.`when`(schedulersFacade.ui()).thenReturn(testScheduler)
        Mockito.`when`(domain.requestContactForm()).thenReturn(single)

        contactPresenter.getContactForm()
        testScheduler.advanceTimeBy(1, TimeUnit.SECONDS)

        verify(contactView, times(1))!!.createContactForm(contactFormResponse)
    }

    @Test
    fun checkIfDisplayErrorIsShowingWhenReceiveError() {
        Mockito.`when`(schedulersFacade.io()).thenReturn(testScheduler)
        Mockito.`when`(schedulersFacade.ui()).thenReturn(testScheduler)
        Mockito.`when`(domain.requestContactForm()).thenReturn(Single.error(MockUtils.getException()))

        contactPresenter.getContactForm()
        testScheduler.advanceTimeBy(1, TimeUnit.SECONDS)

        verify(contactView, times(1))!!.displayError("Exception")


    }
}