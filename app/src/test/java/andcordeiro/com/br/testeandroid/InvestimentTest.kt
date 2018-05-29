package andcordeiro.com.br.testeandroid

import andcordeiro.com.br.testeandroid.entities.ResultScreen
import andcordeiro.com.br.testeandroid.histories.investiment.InvestimentMVP
import andcordeiro.com.br.testeandroid.histories.investiment.InvestimentModel
import andcordeiro.com.br.testeandroid.histories.investiment.InvestimentPresenter
import andcordeiro.com.br.testeandroid.system.retrofit.ApiService
import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import rx.Scheduler
import rx.android.plugins.RxAndroidPlugins
import rx.android.plugins.RxAndroidSchedulersHook
import rx.plugins.RxJavaHooks
import rx.schedulers.Schedulers

class InvestimentTest {

    @Mock
    internal var mocksInvestimentView: InvestimentMVP.View? = null
    @Mock
    internal var mocksInvestimentModel: InvestimentMVP.Model? = null
    @Mock
    internal var mocksApi: ApiService? = null


    var presenter: InvestimentPresenter? = null
    var model: InvestimentModel? = null

    @Before
    @Throws
    fun setup() {

        RxJavaHooks.setOnIOScheduler { Schedulers.immediate() }

        RxJavaHooks.setOnComputationScheduler { Schedulers.immediate() }

        RxJavaHooks.setOnNewThreadScheduler { Schedulers.immediate() }

        val rxAndroidPlugins = RxAndroidPlugins.getInstance()
        rxAndroidPlugins.registerSchedulersHook(object : RxAndroidSchedulersHook() {
            override fun getMainThreadScheduler(): Scheduler {
                return Schedulers.immediate()
            }
        })

        MockitoAnnotations.initMocks(this)
        presenter = InvestimentPresenter(mocksInvestimentModel!!)
        model = InvestimentModel(mocksApi!!)
    }

    @Test
    fun callLoadScreen() {
        val result = ResultScreen()
        Mockito.`when`(mocksInvestimentModel!!.loadScreen()).thenReturn(result)
        val result1 = mocksInvestimentModel!!.loadScreen()
        assert(result1.equals(result))
        verify(mocksInvestimentModel, times(1))!!.loadScreen()
    }

    @Test
    @Throws
    fun returnApiNotNull() {
        var e: Exception? = null
        var result: ResultScreen? = null
        val result1 = ResultScreen()
        Mockito.`when`(mocksApi!!.getScreen()).thenReturn(FakeCall.buildSuccess(result1))
        model!!.loadScreenAsync().subscribe({ it ->
            result = it
        }, { e = it as Exception? })
        verify(mocksApi, times(1))!!.getScreen()
        assertNotNull(result)
        assertNull(e)
    }

    @Test
    @Throws
    fun callSetComponents() {
        var e: Exception? = null
        var result: ResultScreen? = null
        val result1 = ResultScreen()
        Mockito.`when`(mocksApi!!.getScreen()).thenReturn(FakeCall.buildSuccess(result1))
        model!!.loadScreenAsync().subscribe({ it ->
            mocksInvestimentView!!.setComponents(it)
            result = it
        }, { e = it as Exception? })
        verify(mocksApi, times(1))!!.getScreen()
        assertNotNull(result)
        assertNull(e)
        verify(mocksInvestimentView, times(1))!!.setComponents(result!!)
    }

    @Test
    @Throws
    fun returnApiError() {
        var result: ResultScreen? = null
        var e: Exception? = null
        Mockito.`when`(mocksApi!!.getScreen()).thenReturn(FakeCall.buildSuccess(result))
        model!!.loadScreenAsync().subscribe({ it ->
            result = it
        }, { e = it as Exception? })
        verify(mocksApi, times(1))!!.getScreen()
        assertNotNull(e)
    }

    @Test
    @Throws
    fun returnApiCallEquals() {
        val result1 = ResultScreen()
        Mockito.`when`(mocksApi!!.getScreen()).thenReturn(FakeCall.buildSuccess(result1))
        val call = mocksApi!!.getScreen()
        val response = call.execute()
        val resultResponse = response.body() as ResultScreen
        Assert.assertEquals(result1, resultResponse)
    }

    @Test
    @Throws
    fun loadCellsReturnApiCallEquals() {
        val result1 = ResultScreen()
        Mockito.`when`(mocksApi!!.getScreen()).thenReturn(FakeCall.buildSuccess(result1))
        val resultResponse = model!!.loadScreen()
        Assert.assertEquals(result1, resultResponse)
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        RxJavaHooks.reset()
        RxAndroidPlugins.getInstance().reset()
    }
}