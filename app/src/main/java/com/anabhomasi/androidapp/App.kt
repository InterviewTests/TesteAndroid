package com.anabhomasi.androidapp

import android.app.Application
import com.anabhomasi.androidapp.data.models.Form
import com.anabhomasi.androidapp.data.models.Fund

class App : Application() {
    lateinit var cells : Form.Response
    lateinit var funds : Fund.Response

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {
        private var appInstance: com.anabhomasi.androidapp.App? = null

        fun getInstance(): com.anabhomasi.androidapp.App {
            if (appInstance == null) {
                throw IllegalStateException("Configure a classe de Application no AndroidManifest.xml")
            }
            return appInstance!!
        }


    }
}