package com.galdino.testandroid.plataform.views.contact

import com.galdino.testandroid.domain.model.Cell
import com.galdino.testandroid.mvp.Contract

interface ContactContract {
    interface View: Contract.View{
        fun onLoadFormSuccess(cells: List<Cell>)
    }

    interface Presenter: Contract.Presenter<View>{
        fun loadForm()
    }
}