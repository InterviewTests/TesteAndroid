package com.santander.luizlago.testeandroid.ui.fragments.contact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView

import com.santander.luizlago.testeandroid.R
import com.santander.luizlago.testeandroid.api.models.Cell
import com.santander.luizlago.testeandroid.commons.BaseFragment
import com.santander.luizlago.testeandroid.helpers.FieldHelper
import kotlinx.android.synthetic.main.fragment_contact.*
import kotlinx.android.synthetic.main.layout_message_successfull.*

class ContactFragment : BaseFragment<ContactContract.Presenter>(), ContactContract.View {

    override fun createPresente(): ContactContract.Presenter = ContactPresenter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_contact, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.sendAnotherMessageButton.setOnClickListener {
            (this.presenter as ContactPresenter).sendAnotherMessageButtonClicked()
        }
    }

    override fun showLoadingIndication(isShow: Boolean) {
        this.indicatorLoading.visibility = if (isShow) View.VISIBLE else View.GONE
    }

    override fun addCellField(cell: Cell) {
        val editText = EditText(this.context)
        editText.tag = cell
        FieldHelper.configure(editText, cell)
        this.contactContainer.addView(editText)
    }

    override fun addCellText(cell: Cell) {
        val textView = TextView(this.context)
        textView.tag = cell
        FieldHelper.configure(textView, cell)
        this.contactContainer.addView(textView)
    }

    override fun addCellImage(cell: Cell) {
    }

    override fun addCellCheckBox(cell: Cell) {
        val checkBox = CheckBox(this.context)
        checkBox.tag = cell
        FieldHelper.configure(checkBox, cell)
        this.contactContainer.addView(checkBox)
        checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (cell.show != null) {
                val view = FieldHelper.findFieldInContainer(this.contactContainer, cell.show)
                view?.visibility = if(isChecked) View.VISIBLE else View.GONE
            }
        }
    }

    override fun addCellSend(cell: Cell) {
        val button = Button(this.context)
        button.tag = cell
        FieldHelper.configure(button, cell)
        this.contactContainer.addView(button)
        button.setOnClickListener {
            if (!FieldHelper.verifyRequiredFields(this.contactContainer)) {
                (this.presenter as ContactPresenter)?.sendContactValues(FieldHelper.getValuesFromFields(this.contactContainer))
            }
        }
    }

    override fun showLayoutMessageSuccessFull(isShow: Boolean) {
        this.layoutMessageSuccessFull.visibility = if(isShow) View.VISIBLE else View.GONE
    }

    override fun clearFields() {
        FieldHelper.removeAllFields(this.contactContainer)
    }

    companion object {
        @JvmStatic
        fun newInstance() = ContactFragment()
    }

}
