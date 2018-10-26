package com.fuinha11.test.testeandroid.ui.fragment

import android.content.Context
import android.icu.text.IDNA
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.fuinha11.test.testeandroid.R
import com.fuinha11.test.testeandroid.contract.InvestmentFragContracts
import com.fuinha11.test.testeandroid.contract.MainScreenContracts
import com.fuinha11.test.testeandroid.data.model.Investment
import com.fuinha11.test.testeandroid.ui.adapter.MoreInfoAdapter
import kotlinx.android.synthetic.main.fragment_fund.*
import kotlinx.android.synthetic.main.risk_indicator.*
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EFragment
import org.androidannotations.annotations.FragmentArg

@EFragment(R.layout.fragment_fund)
open class InvestmentFragment : Fragment(), InvestmentFragContracts.Fragment {

    lateinit var parent: InvestmentFragContracts.Parent

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        parent = context as InvestmentFragContracts.Parent
    }

    override fun populateInvestmentFragment(investment: Investment) {
        fund_name.text = investment.fundName
        what_is_TV.text = investment.definition
        fund_month.text = String.format("%.2f%%",investment.moreInfo.month.fund)
        cdi_month.text = String.format("%.2f%%",investment.moreInfo.month.CDI)
        fund_year.text = String.format("%.2f%%",investment.moreInfo.year.fund)
        cdi_year.text = String.format("%.2f%%",investment.moreInfo.year.CDI)
        fund_12.text = String.format("%.2f%%",investment.moreInfo.twelvemonths.fund)
        cdi_12.text = String.format("%.2f%%",investment.moreInfo.twelvemonths.CDI)

        info_container.adapter = MoreInfoAdapter(investment.getAllInfo())
        info_container.layoutManager = LinearLayoutManager(activity)

        when (investment.risk){
            1 -> arrowOne.visibility = View.VISIBLE
            2 -> arrowTwo.visibility = View.VISIBLE
            3 -> arrowThree.visibility = View.VISIBLE
            4 -> arrowFour.visibility = View.VISIBLE
            5 -> arrowFive.visibility = View.VISIBLE
        }
    }
}