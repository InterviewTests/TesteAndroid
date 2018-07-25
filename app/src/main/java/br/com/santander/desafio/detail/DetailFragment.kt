package br.com.santander.desafio.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.enzoteles.quickhelp.fragment.HelpFragment
import br.com.santander.desafio.R
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.view.*

class DetailFragment: HelpFragment(),DetailMVP.View{

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val view = inflater!!.inflate(R.layout.detail, container, false)
        return view
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val investment = getString(R.id.lg_bt_investment) as String
        //toolbar.tb_tv_title.setText(investment)
    }

}