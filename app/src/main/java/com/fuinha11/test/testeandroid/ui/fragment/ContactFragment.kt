package com.fuinha11.test.testeandroid.ui.fragment

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentContainer
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.*
import com.fuinha11.test.testeandroid.R
import com.fuinha11.test.testeandroid.contract.ContactFragContracts
import com.fuinha11.test.testeandroid.data.model.Cell
import com.fuinha11.test.testeandroid.ui.view.CellHolder
import kotlinx.android.synthetic.main.fragment_contact.*
import org.androidannotations.annotations.EFragment
import org.androidannotations.annotations.UiThread
import org.androidannotations.annotations.ViewById

@EFragment(R.layout.fragment_contact)
open class ContactFragment : Fragment(), ContactFragContracts.Fragment {

    lateinit var parent: ContactFragContracts.Parent
    var cells: List<CellHolder> = listOf()

    @ViewById(R.id.cells_container)
    lateinit var container: LinearLayout

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        parent = context as ContactFragContracts.Parent
    }


    @UiThread
    override fun populateContactFragment(cells: List<CellHolder>) {
        if (this.cells != cells) {
            this.cells = cells
            for (c in cells) {
                when (c.cell.type) {
                    Cell.CellType.field -> {
                        container.addView(c.editText)
                    }
                    Cell.CellType.text -> {
                        container.addView(c.textView)
                    }
                    Cell.CellType.checkbox -> {
                        container.addView(c.checkBox)
                    }
                    Cell.CellType.send -> {
                        container.addView(c.button)
                    }
                }
            }
        }
    }

    override fun showContactView() {
        thank_you_container.visibility = GONE
        form_container.visibility = VISIBLE
    }

    override fun showThankYouView() {
        thank_you_container.visibility = VISIBLE
        form_container.visibility = GONE
    }

    override fun showCellErrors() {
        for (c in cells) {
            c.checkError()
        }
    }
}