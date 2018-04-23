package com.vctapps.santanderchallenge.core.presentation

import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment
import android.view.View
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.error_view.*
import kotlinx.android.synthetic.main.loading_view.*

abstract class BaseFragment: Fragment(), BaseView {

    @SuppressWarnings("deprecation")
    override fun onAttach(activity: Activity?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(activity)
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun showLoading() {
        loadingView.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loadingView.visibility = View.GONE
    }

    override fun showError() {
        errorView.visibility = View.VISIBLE
    }

    override fun hideError() {
        errorView.visibility = View.GONE
    }

}