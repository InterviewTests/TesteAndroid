package com.rafhack.testeandroid.form

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.rafhack.testeandroid.R
import com.rafhack.testeandroid.base.BaseProgressFragment
import com.rafhack.testeandroid.data.entities.Cell

class FormFragment : BaseProgressFragment(), FormContract.View {

    private var linContainer: LinearLayout? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?): View {
        val view = inflater.inflate(R.layout.fragment_form, container, true)
        linContainer = view.findViewById(R.id.fragment_form_lin_content)
        inflateCells()
        return view
    }

    override fun inflateCells() {
        val manager = DynamicFormManager(linContainer!!)
        manager.cells = arrayListOf(Cell(1, 1, "Nome", 1, false,
                60, null, true),
                Cell(1, 1, "Pedro", 1, false,
                        35, null, true))
    }

}