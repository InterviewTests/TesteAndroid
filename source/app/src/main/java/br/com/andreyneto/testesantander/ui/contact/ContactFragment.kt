package br.com.andreyneto.testesantander.ui.contact

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.andreyneto.testesantander.R
import br.com.andreyneto.testesantander.model.Cell
import kotlinx.android.synthetic.main.fragment_contact.*
import android.support.constraint.ConstraintSet
import android.support.v4.content.ContextCompat
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import br.com.andreyneto.testesantander.convertDpToPixel


class ContactFragment: Fragment(), ContactContract.View {

    private var mPresenter: ContactContract.Presenter? = null

    private var mCells: List<Cell> = listOf()

    private lateinit var lastView: View

    override fun showCells(cells: List<Cell>) {
        mCells = cells
        for(cell in mCells) {
            when (cell.type) {
                1 -> createField(cell)
                2 -> createText(cell)
                4 -> createCheckbox(cell)
                5 -> createSend(cell)
            }
        }
        container.visibility = View.VISIBLE
    }

    private fun createSend(cell: Cell) {
        val view = Button(context!!)
        view.id = cell.id
        view.text = cell.message
        view.setBackgroundColor(ContextCompat.getColor(context!!, R.color.greenDark))
        addView(view, cell.topSpacing, true)
    }
    private fun createCheckbox(cell: Cell) {
        val view = CheckBox(context!!)
        view.id = cell.id
        view.text = cell.message
        view.setBackgroundColor(ContextCompat.getColor(context!!, R.color.greenDark))
        addView(view, cell.topSpacing)
    }

    private fun createField(cell: Cell) {
        val view = EditText(context!!)
        view.id = cell.id
        view.setText(cell.message)
        view.setBackgroundColor(ContextCompat.getColor(context!!, R.color.green))
        addView(view, cell.topSpacing)
    }

    private fun createText(cell: Cell) {
        val view = TextView(context!!)
        view.text = cell.message
        view.id = cell.id
        view.setBackgroundColor(ContextCompat.getColor(context!!, R.color.red))
        addView(view, cell.topSpacing)
    }

    private fun addView(newView: View, topSpacing: Int, last: Boolean = false) {
        newView.layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT, ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        val c = ConstraintSet()
        container.addView(newView)
        c.clone(container)
        val margin = 35.convertDpToPixel(context!!)
        c.connect(newView.id, ConstraintSet.START, container.id, ConstraintSet.START, margin)
        c.connect(newView.id, ConstraintSet.END, container.id, ConstraintSet.END, margin)
        c.connect(newView.id, ConstraintSet.TOP, lastView.id, ConstraintSet.BOTTOM, topSpacing.convertDpToPixel(context!!))
        if(last)
            c.connect(newView.id, ConstraintSet.BOTTOM, container.id, ConstraintSet.BOTTOM, margin)
        c.applyTo(container)
        lastView = newView
    }

    override fun setPresenter(presenter: ContactContract.Presenter) {
        mPresenter = presenter
    }

    override fun onStart() {
        super.onStart()
        mPresenter?.start()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_contact, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lastView = contact_view_title
    }

    companion object {

        fun newInstance(): ContactFragment {
            return ContactFragment()
        }
    }
}