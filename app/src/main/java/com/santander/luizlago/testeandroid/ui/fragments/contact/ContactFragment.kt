package com.santander.luizlago.testeandroid.ui.fragments.contact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.santander.luizlago.testeandroid.R
import com.santander.luizlago.testeandroid.api.models.Cell
import com.santander.luizlago.testeandroid.commons.BaseFragment
import kotlinx.android.synthetic.main.fragment_contact.*

class ContactFragment : BaseFragment<ContactContract.Presenter>(), ContactContract.View {

    override fun createPresente(): ContactContract.Presenter = ContactPresenter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_contact, container, false)
    }

    override fun showLoadingIndication(isShow: Boolean) {
        this.indicatorLoading.visibility = if (isShow) View.VISIBLE else View.GONE
    }

    override fun addCellField(cell: Cell) {
    }

    override fun addCellText(cell: Cell) {
    }

    override fun addCellImage(cell: Cell) {
    }

    override fun addCellCheckBox(cell: Cell) {
    }

    override fun addCellSend(cell: Cell) {
    }


    companion object {
        @JvmStatic
        fun newInstance() = ContactFragment()
    }

}
