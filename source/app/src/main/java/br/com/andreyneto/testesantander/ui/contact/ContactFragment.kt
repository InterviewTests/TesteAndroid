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
import android.support.v7.view.ContextThemeWrapper
import android.util.TypedValue
import android.widget.CheckBox
import android.widget.TextView
import br.com.andreyneto.testesantander.convertDpToPixel
import br.com.andreyneto.testesantander.ui.components.CustomButton
import br.com.andreyneto.testesantander.ui.components.CustomTextInputLayout


class ContactFragment: Fragment(), ContactContract.View {
    override fun showForm() {
        success.visibility = View.GONE
        container.visibility = View.VISIBLE
        for(field in fields) field.editText!!.text = null
    }

    override fun formSended() {
        success.visibility = View.VISIBLE
        container.visibility = View.GONE
    }

    private var mPresenter: ContactContract.Presenter? = null

    private var mCells: List<Cell> = listOf()
    private val fields: MutableList<CustomTextInputLayout> = mutableListOf()

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
        val view = CustomButton(context!!)
        view.id = cell.id
        view.text = cell.message
        addView(view, cell.topSpacing, true)
        view.setOnClickListener {
            var allOk = true
            for(field in fields) if(field.isAppearing() && (field.editText!!.text.isNullOrEmpty() || field.isErrorEnabled)) allOk = false
            if(allOk) mPresenter?.sendForm()
            else mPresenter?.toast(context!!, context!!.getString(R.string.erro_form))
        }
    }
    private fun createCheckbox(cell: Cell) {
        val view = CheckBox(ContextThemeWrapper(context!!, R.style.CheckBox))
        view.id = cell.id
        view.text = cell.message
        view.setTextColor(ContextCompat.getColor(context!!, R.color.gray))
        view.setOnCheckedChangeListener { _, b ->
            val v: CustomTextInputLayout = container.findViewById(cell.show)
            if (b) {
                v.show()
            } else {
                v.hide()
            }

        }
        addView(view, cell.topSpacing)
    }

    private fun createField(cell: Cell) {
        val view = CustomTextInputLayout(context!!, cell)
        view.id = cell.id
        view.setHint(cell.message)
        view.setInputType(cell.typefield)
        addView(view, cell.topSpacing)
        if (cell.hidden) view.hide()
        fields.add(view)
    }

    private fun createText(cell: Cell) {
        val view = TextView(ContextThemeWrapper(context!!, R.style.Title))
        view.text = cell.message
        view.id = cell.id
        view.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
        view.setTextColor(ContextCompat.getColor(context!!, R.color.colorAccent))
        view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
        addView(view, cell.topSpacing)
    }

    private fun addView(newView: View, topSpacing: Int, last: Boolean = false) {
        val top = topSpacing.convertDpToPixel(context!!)
        val height = ConstraintLayout.LayoutParams.WRAP_CONTENT

        newView.layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT, height)
        val c = ConstraintSet()
        container.addView(newView)
        c.clone(container)
        val margin = 35.convertDpToPixel(context!!)
        c.connect(newView.id, ConstraintSet.START, container.id, ConstraintSet.START, margin)
        c.connect(newView.id, ConstraintSet.END, container.id, ConstraintSet.END, margin)
        c.connect(newView.id, ConstraintSet.TOP, lastView.id, ConstraintSet.BOTTOM, top)
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
        contact_view_success_btn_send.setOnClickListener {
            mPresenter?.newMessage()
        }
    }

    companion object {

        fun newInstance(): ContactFragment {
            return ContactFragment()
        }
    }
}