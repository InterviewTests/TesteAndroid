package com.galdino.testandroid.plataform.views.contact

import com.galdino.testandroid.R
import com.galdino.testandroid.plataform.views.base.BaseFragment

class ContactFragment : BaseFragment() {
    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                ContactFragment().apply {
//                    arguments = Bundle().apply {
//                        putString(ARG_PARAM1, param1)
//                        putString(ARG_PARAM2, param2)
//                    }
                }
    }

    override fun getLayoutResource(): Int {
        return R.layout.fragment_contact
    }

    override fun onInitView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
