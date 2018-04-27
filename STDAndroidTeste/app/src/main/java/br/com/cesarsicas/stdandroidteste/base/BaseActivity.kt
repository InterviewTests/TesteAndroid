package br.com.cesarsicas.stdandroidteste.base

import android.support.v7.app.AppCompatActivity
import br.com.cesarsicas.stdandroidteste.AppComponent
import br.com.cesarsicas.stdandroidteste.MainApplication

/**
 * Created by julio on 4/22/18.
 */
open class BaseActivity : AppCompatActivity()  {
    protected val appComponent: AppComponent
        get() {
            val application = application as MainApplication

            return application.appComponent ?: throw IllegalStateException()
        }
}