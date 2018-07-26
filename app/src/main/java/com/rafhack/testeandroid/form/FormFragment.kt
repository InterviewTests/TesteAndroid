package com.rafhack.testeandroid.form

import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.rafhack.testeandroid.R
import com.rafhack.testeandroid.base.BaseProgressFragment
import com.rafhack.testeandroid.data.entities.Cell

class FormFragment : BaseProgressFragment(), FormContract.View {

    private var linContainer: LinearLayout? = null
    private val presenter = FormPresenter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?): View {
        val view = inflater.inflate(R.layout.fragment_form, container, true)
        linContainer = view.findViewById(R.id.fragment_form_lin_content)
        presenter.getCells()
        return view
    }

    override fun setProgress(active: Boolean) {
        if (active) showProgress() else hideProgress()
    }

    override fun showErrorMessage(message: String) {
        Snackbar.make(view?.findViewById(R.id.fragment_base_progress_ctl_root)!!,
                message, Snackbar.LENGTH_LONG).show()
    }

    override fun inflateCells(cells: ArrayList<Cell>) {
        val manager = DynamicFormManager(linContainer!!)
        manager.cells = cells
    }

}