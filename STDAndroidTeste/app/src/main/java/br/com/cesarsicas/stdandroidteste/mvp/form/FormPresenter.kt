package br.com.cesarsicas.stdandroidteste.mvp.form

import android.view.View
import br.com.cesarsicas.stdandroidteste.constants.CellType
import br.com.cesarsicas.stdandroidteste.domains.Cell
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by julio on 4/21/18.
 */

class FormPresenter @Inject constructor() {
    @Inject
    lateinit var interactor: FormInteractor

    var view: FormView? = null
    private var disposable: Disposable? = null

    fun attachView(view: FormView) {
        this.view = view
    }


    fun detachView() {
        this.view = null
    }

    fun getCells() {
        disposable = interactor.getCells()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ cells ->
                    view?.addCells(cells.cells ?: listOf())

                }) { throwable ->

                    view?.showError(throwable.message)
                }
    }


    fun generateDynamicElements(cell: Cell?) {
        val dynamicView: View? = when (cell?.type) {
            CellType.field -> {
                view?.generateEditText(cell)
            }
            CellType.text -> {
                view?.generateTextView(cell)

            }
            CellType.image -> {
                //todo not used yet
                null
            }
            CellType.checkbox -> {
                view?.generateCheckBox(cell)
            }
            CellType.send -> {
                view?.generateButton(cell)
            }
            else -> {
                null
            }
        }

        if (dynamicView != null) {
            view?.configureVisibility(dynamicView, cell)
            view?.configureMargin(dynamicView, cell)
            view?.addView(dynamicView)
        }
    }


}
