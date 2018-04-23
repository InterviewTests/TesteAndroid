package br.com.cesarsicas.stdandroidteste.mvp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.cesarsicas.stdandroidteste.R
import br.com.cesarsicas.stdandroidteste.mvp.form.FormActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //todo splashscreen
        startActivity(Intent(this, FormActivity::class.java))
    }
}
