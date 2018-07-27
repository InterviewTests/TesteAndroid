package com.rafhack.testeandroid.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.FrameLayout
import com.rafhack.testeandroid.R

abstract class BaseProgressFragment : Fragment() {

    private var frmProgress: FrameLayout? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_base_progress, container, false)
        val frmContent = view.findViewById<FrameLayout>(R.id.fragment_base_progress_frm_content)
        frmProgress = view.findViewById(R.id.fragment_base_progress_frm_progress)
        onCreateView(inflater, frmContent)
        return view
    }

    abstract fun onCreateView(inflater: LayoutInflater, container: ViewGroup?): View

    protected fun showProgress() {
        frmProgress?.visibility = VISIBLE
    }

    protected fun hideProgress() {
        frmProgress?.visibility = GONE
    }

}