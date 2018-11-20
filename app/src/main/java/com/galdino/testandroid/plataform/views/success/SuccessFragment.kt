package com.galdino.testandroid.plataform.views.success

import com.galdino.testandroid.R
import com.galdino.testandroid.plataform.views.base.BaseFragment
import com.galdino.testandroid.plataform.views.contact.ContactFragment

class SuccessFragment : BaseFragment() {
    companion object {

        @JvmStatic
        fun newInstance() =
                SuccessFragment()
    }
    private lateinit var mListener: Listener

    override fun getLayoutResource(): Int {
        return R.layout.fragment_success
    }

    interface Listener{
        fun sendNewMessage()
    }

    fun setListener(listener: Listener){
        mListener = listener
    }

    override fun onInitView() {

    }
}
