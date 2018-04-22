package br.com.cesarsicas.stdandroidteste.mvp.form

import br.com.cesarsicas.stdandroidteste.domains.Cell

/**
 * Created by julio on 4/22/18.
 */
interface FormView {
    fun showError(message: String?)
    fun addCells(cells: List<Cell>)
}