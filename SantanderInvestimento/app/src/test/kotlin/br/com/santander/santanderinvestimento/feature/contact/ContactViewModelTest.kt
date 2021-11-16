package br.com.santander.santanderinvestimento.feature.contact

import br.com.santander.santanderinvestimento.feature.contact.di.testApp
import br.com.santander.santanderinvestimento.feature.contact.presentation.ContactContract
import com.nhaarman.mockito_kotlin.mock
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.standalone.StandAloneContext.stopKoin
import org.koin.standalone.inject
import org.koin.test.KoinTest
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Unit tests for the implementation of {@link ContactPresenter}
 */
class ContactViewModelTest : KoinTest {
    private val view: ContactContract.View = mock()
    private val presenter: ContactContract.Presenter by inject()


    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        startKoin(testApp)
        presenter.subscribe(view)
    }

    @Test
    fun `test loadContract presenter`() {
        presenter.loadContact()
    }


    @Test
    fun `test sendContact presenter`() {
        presenter.setContactList(emptyList())
        presenter.sendContact()
        Mockito.verify(view).clearForm()
    }

    @After
    fun after() {
        stopKoin()
    }


}
