package andcordeiro.com.br.testeandroid

import andcordeiro.com.br.testeandroid.entities.ResultContact
import andcordeiro.com.br.testeandroid.histories.contact.ContactMVP
import andcordeiro.com.br.testeandroid.histories.contact.ContactModel
import andcordeiro.com.br.testeandroid.histories.contact.ContactPresenter
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


class ContactTest {

    @Mock
    internal var mocksContactView: ContactMVP.View? = null
    @Mock
    internal var mocksContactModel: ContactMVP.Model? = null
    @Mock
    internal var mocksApi: ApiService? = null


    var presenter: ContactPresenter? = null
    var model: ContactModel? = null

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
        presenter = ContactPresenter(mocksContactModel!!)
        model = ContactModel(mocksApi!!)
    }

    @Test
    fun callLoadCells() {
        val result = ResultContact()
        Mockito.`when`(mocksContactModel!!.loadCells()).thenReturn(result)
        val result1 = mocksContactModel!!.loadCells()
        assert(result1.equals(result))
        verify(mocksContactModel, times(1))!!.loadCells()
    }

    @Test
    @Throws
    fun returnApiNotNull() {
        var e: Exception? = null
        var result: ResultContact? = null
        val result1 = ResultContact()
        Mockito.`when`(mocksApi!!.getCells()).thenReturn(FakeCall.buildSuccess(result1))
        model!!.loadCellsAsync().subscribe({ it ->
            result = it
        }, { e = it as Exception? })
        verify(mocksApi, times(1))!!.getCells()
        assertNotNull(result)
        assertNull(e)
    }

    @Test
    @Throws
    fun callCreateComponents() {
        var e: Exception? = null
        var result: ResultContact? = null
        val result1 = ResultContact()
        Mockito.`when`(mocksApi!!.getCells()).thenReturn(FakeCall.buildSuccess(result1))
        model!!.loadCellsAsync().subscribe({ it ->
            mocksContactView!!.createComponents(it)
            result = it
        }, { e = it as Exception? })
        verify(mocksApi, times(1))!!.getCells()
        assertNotNull(result)
        assertNull(e)
        verify(mocksContactView, times(1))!!.createComponents(result!!)
    }

    @Test
    @Throws
    fun returnApiError() {
        var result: ResultContact? = null
        var e: Exception? = null
        Mockito.`when`(mocksApi!!.getCells()).thenReturn(FakeCall.buildSuccess(result))
        model!!.loadCellsAsync().subscribe({ it ->
            result = it
        }, { e = it as Exception? })
        verify(mocksApi, times(1))!!.getCells()
        assertNotNull(e)
    }

    @Test
    @Throws
    fun returnApiCallEquals() {
        val result1 = ResultContact()
        Mockito.`when`(mocksApi!!.getCells()).thenReturn(FakeCall.buildSuccess(result1))
        val call = mocksApi!!.getCells()
        val response = call.execute()
        val resultResponse = response.body() as ResultContact
        Assert.assertEquals(result1, resultResponse)
    }

    @Test
    @Throws
    fun loadCellsReturnApiCallEquals() {
        val result1 = ResultContact()
        Mockito.`when`(mocksApi!!.getCells()).thenReturn(FakeCall.buildSuccess(result1))
        val resultResponse = model!!.loadCells()
        Assert.assertEquals(result1, resultResponse)
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        RxJavaHooks.reset()
        RxAndroidPlugins.getInstance().reset()
    }
}