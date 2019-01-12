package br.com.rafael.santanderteste.presentation.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import br.com.rafael.santanderteste.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btInvestiment.setOnClickListener(this)
        btContact.setOnClickListener(this)

        }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.btInvestiment -> Log.i("log", "investimento fragment")
            R.id.btContact -> Log.i("log", "contato fragment")
            else -> {}
        }
    }
}
