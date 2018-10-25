package com.fuinha11.test.testeandroid.ui.activity

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import com.fuinha11.test.testeandroid.R
import com.fuinha11.test.testeandroid.contract.BaseContracts
import com.fuinha11.test.testeandroid.util.extension.hideKeyboard
import com.fuinha11.test.testeandroid.util.extension.toast
import org.androidannotations.annotations.AfterInject
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EActivity
import org.androidannotations.annotations.UiThread


@EActivity
abstract class BaseActivity
    : AppCompatActivity(),
        BaseContracts.View {

    private lateinit var loadingDialog : View

    private lateinit var loadingMessage: TextView

    @UiThread
    override fun showLoading(message: String) {
        this.dismissKeyboard()
        if (!::loadingDialog.isInitialized) {
            val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = inflater.inflate(R.layout.progress_dialog, this.findViewById(android.R.id.content) as ViewGroup)
            loadingMessage = view.findViewById(R.id.progressBarHolder_message) as TextView
            loadingDialog = view.findViewById(R.id.progressBarHolder)
        }
        loadingMessage.text = message
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        loadingDialog.visibility = View.VISIBLE
    }

    @UiThread
    override fun hideLoading() {
        if (::loadingDialog.isInitialized) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            loadingDialog.visibility = View.GONE
        }
    }

    override fun dismissKeyboard() {
        hideKeyboard()
    }

    override fun showToast(message: String, length: Int) {
        toast(message, length)
    }

    @AfterViews
    abstract fun initComponents()
}