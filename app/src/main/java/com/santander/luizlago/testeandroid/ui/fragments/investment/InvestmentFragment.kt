package com.santander.luizlago.testeandroid.ui.fragments.investment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Space
import android.widget.Toast

import com.santander.luizlago.testeandroid.R
import com.santander.luizlago.testeandroid.api.models.Cell
import com.santander.luizlago.testeandroid.api.models.Fund
import com.santander.luizlago.testeandroid.api.models.InfoValue
import com.santander.luizlago.testeandroid.commons.BaseFragment
import com.santander.luizlago.testeandroid.enums.Type
import com.santander.luizlago.testeandroid.helpers.FieldHelper
import com.santander.luizlago.testeandroid.ui.fragments.investment.components.*
import kotlinx.android.synthetic.main.fragment_investment.*

class InvestmentFragment : BaseFragment<InvestmentContract.Presenter>(), InvestmentContract.View{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_investment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun createPresente(): InvestmentContract.Presenter = InvestmentPresenter(this)

    override fun showLoadingIndication(isShow: Boolean) {
    }

    override fun addHeaderView(fund: Fund) {
        val headerView = InvestmentHeaderView(this.context!!)
        headerView.setFund(fund)
        this.investmentContainer.addView(headerView)
    }

    override fun addRiskView(fund: Fund) {
        val riskView = InvestmentRiskView(this.context!!)
        riskView.setFund(fund)
        this.investmentContainer.addView(riskView)
    }

    override fun addMoreInfoView(fund: Fund) {
        val moreInfoView = InvestmentMoreInfoView(this.context!!)
        moreInfoView.setFund(fund)
        this.investmentContainer.addView(moreInfoView)
    }

    override fun addInfoValues(infoValues: List<InfoValue>) {
        infoValues.forEach {
            val infoValue = InvestmentInfoView(this.context!!)
            infoValue.setInfoValue(it)
            this.investmentContainer.addView(infoValue)
        }
    }

    override fun addDownInfoValues(infoValues: List<InfoValue>) {
        infoValues.forEach {
            val infoValue = InvestmentDownInfoView(this.context!!)
            infoValue.setDownInfoValue(it)
            this.investmentContainer.addView(infoValue)
        }
    }

    override fun addInvestmentButton() {
        val cell = Cell(0, Type.SEND.value,getString(R.string.invest), null, false, 24.0f, null, false )
        val button = Button(this.context)
        button.tag = cell
        FieldHelper.configure(button, cell)
        this.investmentContainer.addView(button)
        button.setOnClickListener {
            Toast.makeText(this.context, getString(R.string.investmentButtonClicked), Toast.LENGTH_SHORT).show()
        }
        val spaceHeight = FieldHelper.convertDpToPx(this.context!!, 24)
        val space = Space(this.context!!)
        val layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, spaceHeight)
        space.layoutParams = layoutParams
        this.investmentContainer.addView(space)
    }


    companion object {
        @JvmStatic
        fun newInstance() = InvestmentFragment()
    }

}
