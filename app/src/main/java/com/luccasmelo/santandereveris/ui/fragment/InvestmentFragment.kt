package com.luccasmelo.santandereveris.ui.fragment

import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.luccasmelo.santandereveris.R
import com.luccasmelo.santandereveris.data.model.DataInfoField
import com.luccasmelo.santandereveris.data.model.InvestmentInformation
import com.luccasmelo.santandereveris.databinding.InvestmentFragmentBinding
import com.luccasmelo.santandereveris.ui.adapter.ContactAdapter
import com.luccasmelo.santandereveris.ui.adapter.InvestmentInformationAdapters
import com.luccasmelo.santandereveris.view_model.ContactViewModel
import com.luccasmelo.santandereveris.view_model.InvestmentViewModel

class InvestmentFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: InvestmentFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.investment_fragment, container, false)
        val viewModel = InvestmentViewModel()

        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel

        viewModel.investmentInformation.observe(this, Observer { investimentInformation ->
            setupLayoutList(binding, investimentInformation)

        })

        return binding.root
    }

    private fun setupLayoutList(binding: InvestmentFragmentBinding, investimentInformation: InvestmentInformation) {
        binding.rcvMoreInfo.layoutManager = LinearLayoutManager(context)
        binding.rcvInfo.layoutManager = LinearLayoutManager(context)
        binding.rcvDown.layoutManager = LinearLayoutManager(context)

        val moreInfoArrayList = ArrayList<DataInfoField>()
        moreInfoArrayList.add(
            DataInfoField(
                DataInfoField.FieldDataInfoEnum.MONTH,
                investimentInformation.screen!!.moreInfo!!.month!!
            )
        )
        moreInfoArrayList.add(
            DataInfoField(
                DataInfoField.FieldDataInfoEnum.YEAR,
                investimentInformation.screen!!.moreInfo!!.year!!
            )
        )
        moreInfoArrayList.add(
            DataInfoField(
                DataInfoField.FieldDataInfoEnum.MONTH_12,
                investimentInformation.screen!!.moreInfo!!.months12!!
            )
        )

        binding.rcvMoreInfo.adapter =
                InvestmentInformationAdapters(context!!,
                    R.layout.info_item,
                    moreInfoArrayList as ArrayList<Any>)
        binding.rcvInfo.adapter = InvestmentInformationAdapters(
            context!!,
            R.layout.more_info_item,
            investimentInformation.screen!!.info!! as ArrayList<Any>
        )
        binding.rcvDown.adapter = InvestmentInformationAdapters(
            context!!,
            R.layout.down_info_item,
            investimentInformation.screen!!.downInfo!! as ArrayList<Any>
        )


    }


}