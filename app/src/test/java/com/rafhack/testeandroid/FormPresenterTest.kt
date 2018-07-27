package com.rafhack.testeandroid

import com.rafhack.testeandroid.data.domain.FormInteractor
import com.rafhack.testeandroid.data.entities.Cell
import com.rafhack.testeandroid.form.FormContract
import com.rafhack.testeandroid.form.FormPresenter
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FormPresenterTest {

    @Mock
    private val view: FormContract.View? = null
    @Mock
    private val interactor: FormInteractor? = null
    private lateinit var presenter: FormPresenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        presenter = FormPresenter(view!!)
        presenter.interactor = interactor!!
    }

    @Test
    fun loadCellsSuccess() {
        val response = Mockito.mock(ArrayList::class.java)
        Mockito.doReturn(Single.just(response)).`when`(interactor)?.getCells()
        presenter.getCells()
        @Suppress("UNCHECKED_CAST")
        verify(view!!).inflateCells(response as ArrayList<Cell>)
    }

    @Test
    fun loadCellsError() {
        val response = Exception("")
        val single: Single<Throwable> = Single.error(response)
        Mockito.doReturn(single).`when`(interactor)?.getCells()
        presenter.getCells()
        verify(view!!).showErrorMessage(response.message!!)
    }

}