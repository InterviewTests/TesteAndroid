package com.galdino.testandroid.plataform.views.contact


import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LifecycleRegistry
import com.galdino.testandroid.data.entity.CellResponseBody
import com.galdino.testandroid.domain.IRepository
import com.galdino.testandroid.domain.executor.JobScheduler
import com.galdino.testandroid.domain.executor.UIScheduler
import com.galdino.testandroid.domain.interactor.UseCase
import com.galdino.testandroid.domain.interactor.cell.GetCell
import com.galdino.testandroid.domain.interactor.cell.ICelUseCaseFactory
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class ContactPresenterTeste {
    private var mTestScheduler = TestScheduler()
    private val mRepository: IRepository = Mockito.mock(IRepository::class.java)
    private val useCaseFactory: ICelUseCaseFactory = Mockito.mock(ICelUseCaseFactory::class.java)
    private val mView: ContactContract.View = Mockito.mock(ContactContract.View::class.java)

    lateinit var mPresenter: ContactContract.Presenter

    val uiScheduler = object :UIScheduler{
        override fun getScheduler(): Scheduler {
            return mTestScheduler
        }
    }
    val jobScheduler = object :JobScheduler {
        override fun getScheduler(): Scheduler {
            return mTestScheduler
        }
    }

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        val lifecycle = LifecycleRegistry(mock(LifecycleOwner::class.java))
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        `when`(mView.getLifecycle()).thenReturn(lifecycle)

        mPresenter = ContactPresenter(useCaseFactory)
        mPresenter.attach(mView)
    }

    @Test
    fun loadForm_listCells_error(){
        `when`(useCaseFactory.loadForm()).thenReturn(object : UseCase<CellResponseBody, GetCell.Params>(uiScheduler, jobScheduler) {
            override fun buildUseCaseObservable(params: GetCell.Params): Single<CellResponseBody> {
                return Single.error(Exception())
            }

        })
        mPresenter.loadForm()
        mTestScheduler.triggerActions()
        verify(mView).showDefaultErrorOnLoadForm()
    }
}