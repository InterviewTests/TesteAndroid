package com.anabhomasi.androidapp.views.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.anabhomasi.androidapp.App
import com.anabhomasi.androidapp.R
import com.anabhomasi.androidapp.data.models.Form
import com.anabhomasi.androidapp.data.models.Fund
import com.anabhomasi.androidapp.data.services.ServiceManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashActivity : AppCompatActivity() {
    private var form : Form.Response? = null
    private var funds : Fund.Response? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun onStart() {
        super.onStart()
        getDataFromRemote()
    }

    private fun getDataFromRemote(){
        val taskService = ServiceManager.buildService()

        val callForm = taskService.getCells()
        callForm.enqueue(object : Callback<Form.Response> {
            override fun onResponse(request: Call<Form.Response>, response: Response<Form.Response>) {
                form = response.body()
                validateData()
            }

            override fun onFailure(request: Call<Form.Response>, t: Throwable) {
                callForm.clone().enqueue(this)
            }
        })

        val callFunds = taskService.getFunds()
        callFunds.enqueue(object : Callback<Fund.Response> {
            override fun onResponse(request: Call<Fund.Response>, response: Response<Fund.Response>) {
                funds = response.body()
                validateData()
            }

            override fun onFailure(request: Call<Fund.Response>, t: Throwable) {
                callFunds.clone().enqueue(this)
            }
        })
    }

    private fun validateData() {
        if (form != null && funds != null){
            val intent = Intent(this, MainActivity::class.java)

            App.getInstance().form = form!!
            App.getInstance().funds = funds!!

            this.startActivity(intent)
            this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        } else
            Log.e("validationError", "cells = $form and funds = $funds")
    }
}