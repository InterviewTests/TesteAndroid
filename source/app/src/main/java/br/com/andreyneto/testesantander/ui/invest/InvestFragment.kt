package br.com.andreyneto.testesantander.ui.invest

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.andreyneto.testesantander.R
import br.com.andreyneto.testesantander.convertDpToPixel
import br.com.andreyneto.testesantander.model.Screen
import kotlinx.android.synthetic.main.fragment_invest.*

class InvestFragment : Fragment(), InvestContract.View {

    private var mPresenter: InvestContract.Presenter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_invest, container, false)
    }

    override fun setPresenter(presenter: InvestContract.Presenter) {
        mPresenter = presenter
    }

    override fun showData(model: Screen) {
        invest_title.text = model.title
        invest_investName.text = model.fundName
        invest_whatIs.text = model.whatIs
        invest_definition.text = model.definition
        invest_riskTitle.text = model.riskTitle
        invest_infoTitle.text = model.infoTitle

        setRisk(model.risk)

        invest_list_moreInfo.adapter = MoreInfoAdapter(model.moreInfo, context!!)
        invest_list_moreInfo.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        invest_list_info.adapter = InfoAdapter(model.info + model.downInfo, context!!, mPresenter)
        invest_list_info.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        invest_btn.setOnClickListener {
            mPresenter?.toast(context!!, context!!.getString(R.string.investir))
        }

        invest_share.setOnClickListener {
            mPresenter?.toast(context!!, context!!.getString(R.string.compartilhar))
        }

        container.visibility = View.VISIBLE
        container.requestFocus()
    }

    override fun onStart() {
        super.onStart()
        mPresenter?.start()
    }

    private fun setRisk(risk: Int) {
        lateinit var riskView: View
        var bias = 0f
        when (risk) {
            1 -> { riskView = invest_risk_1; bias = 0.08f }
            2 -> { riskView = invest_risk_2; bias = 0.285f }
            3 -> { riskView = invest_risk_3; bias = 0.5f }
            4 -> { riskView = invest_risk_4; bias = 0.715f }
            5 -> { riskView = invest_risk_5; bias = 0.92f }
        }
        var params = riskView.layoutParams as ConstraintLayout.LayoutParams?
        params?.setMargins(params.leftMargin, 49.convertDpToPixel(context!!), params.rightMargin, params.bottomMargin)
        params?.height = 15.convertDpToPixel(context!!)
        riskView.layoutParams = params

        params = invest_risk_indicator.layoutParams as ConstraintLayout.LayoutParams?
        params?.horizontalBias = bias
        invest_risk_indicator.layoutParams = params
    }

    companion object {

        fun newInstance(): InvestFragment {
            return InvestFragment()
        }
    }
}
