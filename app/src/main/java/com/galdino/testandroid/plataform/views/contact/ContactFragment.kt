package com.galdino.testandroid.plataform.views.contact

import android.support.v7.widget.LinearLayoutManager
import com.galdino.testandroid.R
import com.galdino.testandroid.domain.model.Cell
import com.galdino.testandroid.plataform.views.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_contact.*
import org.koin.android.ext.android.inject

class ContactFragment: BaseFragment(), ContactContract.View, FormAdapter.Listener {
    private val mPresenter: ContactContract.Presenter by inject()
    private lateinit var mListener: Listener
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
        val formAdapter = FormAdapter(cells, this)
        rvForm.adapter = formAdapter
        rvForm.layoutManager = LinearLayoutManager(context)
    }

    override fun onSendClicked(cells: List<Cell>) {
        mPresenter.onSendClicked(cells, context)
    }

    override fun onLoading(isLoading: Boolean) {
        onLoading(pbLoading,isLoading)
    }

    override fun showDefaultErrorOnLoadForm() {
        showLongToast(R.string.error_on_loading_form)
    }

    override fun showError(message: String) {
        showLongToast(message)
    }

    fun setListener(listener: ContactFragment.Listener){
        mListener = listener
    }

    interface Listener{
        fun onMessageSendSuccess()
    }

    override fun callSuccessScreen() {
        mListener.onMessageSendSuccess()
    }
}
