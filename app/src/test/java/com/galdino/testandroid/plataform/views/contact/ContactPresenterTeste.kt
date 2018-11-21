package com.galdino.testandroid.plataform.views.contact


import com.galdino.testandroid.data.entity.CellResponseBody
import com.galdino.testandroid.domain.IRepository
import com.galdino.testandroid.domain.Observer
import com.galdino.testandroid.domain.executor.JobScheduler
import com.galdino.testandroid.domain.executor.UIScheduler
import com.galdino.testandroid.domain.interactor.cell.GetCell
import com.galdino.testandroid.domain.interactor.cell.ICelUseCaseFactory
import io.reactivex.Scheduler
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class ContactPresenterTeste {
    private var mTestScheduler = TestScheduler()
    private val mRepository: IRepository = Mockito.mock(IRepository::class.java)
    private val useCaseFactory: ICelUseCaseFactory = Mockito.mock(ICelUseCaseFactory::class.java)
    private val mView: ContactContract.View = Mockito.mock(ContactContract.View::class.java)

    lateinit var mPresenter: ContactContract.Presenter

    @Before
    fun setUp() {
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

        val getCell = GetCell(mRepository, uiScheduler, jobScheduler)
        MockitoAnnotations.initMocks(this)
        `when`(useCaseFactory.loadForm()).thenReturn(getCell)

        mPresenter = ContactPresenter(useCaseFactory)
//        mPresenter.attach(mView)
    }

    @Test
    fun loadForm_listCells_error(){
        val loadForm = useCaseFactory.loadForm()

    }
}