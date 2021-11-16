package br.com.santander.santanderinvestimento.investment

import br.com.santander.santanderinvestimento.feature.investiment.presentation.InvestmentContract
import br.com.santander.santanderinvestimento.investment.di.testApp
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
 * Unit tests for the implementation of {@link InvestmentPresenter}
 */
class InvestmentViewModelTest : KoinTest {
    private val view: InvestmentContract.View = mock()
    private val presenter: InvestmentContract.Presenter by inject()

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        startKoin(testApp)
        presenter.subscribe(view)
    }

    @Test
    fun `test loadFeed presenter`() {
        presenter.loadFeed()
    }

    @Test
    fun `test clickShare presenter`() {
        presenter.clickShare()
        Mockito.verify(view).showMessage("Não disponível")
    }

    @Test
    fun `test clickInvest presenter`() {
        presenter.clickInvest()
        Mockito.verify(view).showMessage("Não disponível")
    }

    @Test
    fun `test clickDownload presenter`() {
        presenter.clickDownload("")
        Mockito.verify(view).showMessage("Não disponível")
    }

    @After
    fun after() {
        stopKoin()
    }
}