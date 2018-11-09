package br.com.gservices.santanderteste

import br.com.gservices.santanderteste.pages.contacts.interfaces.ContractContactsInterface
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

class ContactPresenterTest : KoinTest {
    private val view: ContractContactsInterface.View = mock()
    private val presenter: ContractContactsInterface.Presenter by inject()


    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        startKoin(testAppModule)
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
