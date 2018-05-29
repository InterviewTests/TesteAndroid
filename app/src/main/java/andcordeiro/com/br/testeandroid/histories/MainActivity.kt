package andcordeiro.com.br.testeandroid.histories

import andcordeiro.com.br.testeandroid.R
import andcordeiro.com.br.testeandroid.histories.contact.ContactFragment
import andcordeiro.com.br.testeandroid.histories.investiment.InvestimentFragment
import andcordeiro.com.br.testeandroid.system.extensions.hide
import andcordeiro.com.br.testeandroid.system.extensions.show
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnContact.setOnClickListener { contactClick() }
        btnInvest.setOnClickListener { investClick() }
        btnInvest.callOnClick()
    }

    fun contactClick() {
        pbInvest.hide()
        pbContact.show()
        btnContact.setBackgroundColor(ContextCompat.getColor(this, R.color.redButtonChecked))
        btnInvest.setBackgroundColor(ContextCompat.getColor(this, R.color.rosso_corsa))
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fl_container, ContactFragment.newInstance())
        transaction.commit()
    }

    fun investClick() {
        pbContact.hide()
        pbInvest.show()
        btnContact.setBackgroundColor(ContextCompat.getColor(this, R.color.rosso_corsa))
        btnInvest.setBackgroundColor(ContextCompat.getColor(this, R.color.redButtonChecked))
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fl_container, InvestimentFragment.newInstance())
        transaction.commit()
    }
}
