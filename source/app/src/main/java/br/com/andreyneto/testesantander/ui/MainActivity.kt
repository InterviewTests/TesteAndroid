package br.com.andreyneto.testesantander.ui

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import br.com.andreyneto.testesantander.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewpager.adapter = FragmentAdapter(this, supportFragmentManager)

        tabs.setupWithViewPager(viewpager)
    }

}