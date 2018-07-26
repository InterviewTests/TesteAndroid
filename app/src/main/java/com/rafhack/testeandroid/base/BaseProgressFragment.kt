package com.rafhack.testeandroid.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

open class BaseProgressFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {



        return super.onCreateView(inflater, container, savedInstanceState)
    }

    protected fun showProgress() {

    }

    protected fun hideProgress() {

    }

}