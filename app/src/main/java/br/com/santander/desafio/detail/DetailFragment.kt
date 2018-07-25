package br.com.santander.desafio.detail

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.os.Handler
import android.support.annotation.Nullable
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.enzoteles.quickhelp.fragment.HelpFragment
import br.com.santander.desafio.Constants
import br.com.santander.desafio.MainActivity
import br.com.santander.desafio.R
import br.com.santander.desafio.detail.adapter.DownInfoListAdapter
import br.com.santander.desafio.detail.adapter.InfoListAdapter
import br.com.santander.desafio.webservice.fund.DownInfoItem
import br.com.santander.desafio.webservice.fund.InfoItem
import br.com.santander.desafio.webservice.fund.ResponseFund
import kotlinx.android.synthetic.main.detail.*
import kotlinx.android.synthetic.main.item_more_info.*
import kotlinx.android.synthetic.main.toolbar_details.*
import kotlinx.android.synthetic.main.toolbar_details.view.*

class DetailFragment: HelpFragment(),DetailMVP.View{

    lateinit var presenter: DetailPresenter
    lateinit var adapterInfo: InfoListAdapter
    lateinit var adapterDonwInfo: DownInfoListAdapter
    lateinit var layoutManager: LinearLayoutManager
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val view = inflater!!.inflate(R.layout.detail, container, false)
        return view
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = DetailPresenter()
        initUI()
        initDate()
    }

    override fun initUI(){

        var investimento = getString(R.string.lg_bt_investment)
        toolbar_detail.dt_tb_tv_title.setText(investimento)
    }

    override fun initDate() {
        presenter.getFunc()?.observe(activity as MainActivity, object : Observer<ResponseFund> {
            override fun onChanged(@Nullable response: ResponseFund?) {
                if (response != null) {
                    setDataView(response)
                }
            }
        })
    }

    override fun setDataView(response: ResponseFund) {
        dt_tv_title.setText(response!!.screen!!.title)
        dt_tv_fund_name.setText(response!!.screen!!.fundName)
        dt_tv_what.setText(response!!.screen!!.whatIs)
        dt_tv_definition.setText(response!!.screen!!.definition)
        dt_tv_risk_title.setText(response!!.screen!!.riskTitle)
        dt_tv_info_title.setText(response!!.screen!!.infoTitle)

        dt_mounth_tv_found.setText("${response!!.screen!!.moreInfo!!.month!!.fund}%")
        dt_mounth_tv_cdi.setText("${response!!.screen!!.moreInfo!!.month!!.cDI}%")

        dt_year_tv_found.setText("${response!!.screen!!.moreInfo!!.year!!.fund}%")
        dt_year_tv_cdi.setText("${response!!.screen!!.moreInfo!!.year!!.cDI}%")

        dt_12m_tv_found.setText("${response!!.screen!!.moreInfo!!.jsonMember12months!!.fund}%")
        dt_12m_tv_cdi.setText("${response!!.screen!!.moreInfo!!.jsonMember12months!!.cDI}%")

        setRecyclerviewInfo(response.screen!!.info)
        setRecyclerviewDownInfo(response.screen!!.downInfo)



    }


    override fun setRecyclerviewInfo(infos: List<InfoItem?>?) {

        dt_rv_info.layoutManager = layoutManager
        dt_rv_info.setHasFixedSize(true)
        adapterInfo = InfoListAdapter(infos, Constants.context!!)
        dt_rv_info.adapter = adapterInfo
    }

    override fun setRecyclerviewDownInfo(downInfos: List<DownInfoItem?>?) {

        layoutManager = LinearLayoutManager(Constants.context, LinearLayoutManager.VERTICAL, false)
        dt_rv_down_info.layoutManager = layoutManager
        dt_rv_down_info.setHasFixedSize(true)
        adapterDonwInfo = DownInfoListAdapter(downInfos, Constants.context!!)
        dt_rv_down_info.adapter = adapterDonwInfo
    }


}