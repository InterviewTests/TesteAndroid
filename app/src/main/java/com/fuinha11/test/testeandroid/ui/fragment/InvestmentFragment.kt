package com.fuinha11.test.testeandroid.ui.fragment

import android.content.Context
import android.support.v4.app.Fragment
import com.fuinha11.test.testeandroid.R
import com.fuinha11.test.testeandroid.contract.InvestmentFragContracts
import com.fuinha11.test.testeandroid.contract.MainScreenContracts
import com.fuinha11.test.testeandroid.data.model.Investment
import kotlinx.android.synthetic.main.fragment_fund.*
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
        fund_month.text = investment.moreInfo.month.fund.toString()
        cdi_month.text = investment.moreInfo.month.CDI.toString()
        fund_year.text = investment.moreInfo.year.fund.toString()
        cdi_year.text = investment.moreInfo.year.CDI.toString()
        fund_12.text = investment.moreInfo.twelvemonths.fund.toString()
        cdi_12.text = investment.moreInfo.twelvemonths.CDI.toString()
    }
}