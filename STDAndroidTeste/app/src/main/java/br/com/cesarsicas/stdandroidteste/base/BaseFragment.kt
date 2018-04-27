package br.com.cesarsicas.stdandroidteste.base


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.cesarsicas.stdandroidteste.AppComponent
import br.com.cesarsicas.stdandroidteste.MainApplication

import br.com.cesarsicas.stdandroidteste.R


/**
 * A simple [Fragment] subclass.
 */
open class BaseFragment : Fragment() {
    protected val appComponent: AppComponent
        get() {
            val application = context.applicationContext as MainApplication

            return application.appComponent ?: throw IllegalStateException()
        }

}// Required empty public constructor
