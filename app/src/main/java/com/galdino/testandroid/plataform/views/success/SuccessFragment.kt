package com.galdino.testandroid.plataform.views.success

import android.view.View
import com.galdino.testandroid.R
import com.galdino.testandroid.plataform.views.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_success.*

class SuccessFragment : BaseFragment(), View.OnClickListener {
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
        btSendNewMessage.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            btSendNewMessage.id->{
                mListener.sendNewMessage()
            }
        }
    }
}
