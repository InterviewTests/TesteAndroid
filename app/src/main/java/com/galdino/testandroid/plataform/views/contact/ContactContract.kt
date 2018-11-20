package com.galdino.testandroid.plataform.views.contact

import android.content.Context
import com.galdino.testandroid.domain.model.Cell
import com.galdino.testandroid.mvp.Contract

interface ContactContract {
    interface View: Contract.View{
        fun onLoadFormSuccess(cells: List<Cell>)
        fun onLoading(isLoading: Boolean)
    }

    interface Presenter: Contract.Presenter<View>{
        fun loadForm()
        fun onSendClicked(cells: List<Cell>, context: Context?)
    }
}