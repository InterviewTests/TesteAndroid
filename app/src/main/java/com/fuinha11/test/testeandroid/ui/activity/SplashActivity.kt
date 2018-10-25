package com.fuinha11.test.testeandroid.ui.activity

import android.support.v7.app.AppCompatActivity
import com.fuinha11.test.testeandroid.R
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EActivity

@EActivity(R.layout.activity_splash)
open class SplashActivity : AppCompatActivity() {
    @AfterViews
    open fun redirect(){
        MainActivity_.intent(this).start()
    }
}
