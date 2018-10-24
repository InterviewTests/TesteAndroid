package com.fuinha11.test.testeandroid.ui

import android.support.v7.app.AppCompatActivity
import com.fuinha11.test.testeandroid.R
import com.fuinha11.test.testeandroid.service.api.ApiService
import com.fuinha11.test.testeandroid.service.api.data.response.CellsResponse
import com.fuinha11.test.testeandroid.service.api.data.response.FundResponse
import com.fuinha11.test.testeandroid.util.extension.toast
import org.androidannotations.annotations.Bean
import org.androidannotations.annotations.Click
import org.androidannotations.annotations.EActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@EActivity(R.layout.activity_splash)
open class SplashActivity : AppCompatActivity() {}
