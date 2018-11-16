package com.galdino.testandroid.plataform.views.contact

import android.support.v7.widget.LinearLayoutManager
import com.galdino.testandroid.R
import com.galdino.testandroid.domain.model.Cell
import com.galdino.testandroid.plataform.views.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_contact.*
import org.koin.android.ext.android.inject

class ContactFragment: BaseFragment(), ContactContract.View {
    private val mPresenter: ContactContract.Presenter by inject()
    companion object {

        @JvmStatic
        fun newInstance() =
                ContactFragment()
    }

    override fun getLayoutResource(): Int {
        return R.layout.fragment_contact
    }

    override fun onInitView() {
        mPresenter.attach(this)
        mPresenter.loadForm()
    }

    override fun onLoadFormSuccess(cells: List<Cell>) {
        val formAdapter = FormAdapter(cells)
        rvForm.adapter = formAdapter
        rvForm.layoutManager = LinearLayoutManager(context)
    }
}
