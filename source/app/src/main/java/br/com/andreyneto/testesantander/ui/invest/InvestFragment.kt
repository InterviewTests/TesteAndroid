package br.com.andreyneto.testesantander.ui.invest

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.andreyneto.testesantander.R
import br.com.andreyneto.testesantander.convertDpToPixel
import br.com.andreyneto.testesantander.model.Screen
import kotlinx.android.synthetic.main.fragment_invest.*

class InvestFragment : Fragment(), InvestContract.View {

    private var mPresenter: InvestContract.Presenter? = null
    private var listRisk: List<View>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_invest, container, false)
        listRisk = listOf(fund_risk_1, fund_risk_2, fund_risk_3, fund_risk_4, fund_risk_5)
        return view
    }

    override fun setPresenter(presenter: InvestContract.Presenter) {
        mPresenter = presenter
    }

    override fun showData(model: Screen) {
        fund_title.text = model.title
        fund_fundName.text = model.fundName
        fund_whatIs.text = model.whatIs
        fund_definition.text = model.definition
        fund_riskTitle.text = model.riskTitle
        fund_infoTitle.text = model.infoTitle

        setRisk(model.risk)

        val size = 26.convertDpToPixel(context!!)

//        val moreInfoAdapter = InfoBaseAdapter(listOf(model.moreInfo), this)
//        mListMoreInfo.setAdapter(moreInfoAdapter)
//        val lp = mListMoreInfo.getLayoutParams()
//        lp.height = moreInfo.size * convertDpToPixel(sizeLine)
//        mListMoreInfo.setLayoutParams(lp)

//        val infoAdapter = InfoBaseAdapter(model.info!!, context!!)
//        fund_list_info.adapter = infoAdapter
//        val lpInfo = fund_list_info.layoutParams
//        lpInfo.height = model.info!!.size * size
//        lpInfo.resolveLayoutDirection(View.LAYOUT_DIRECTION_INHERIT)
//        fund_list_info.layoutParams = lpInfo

    }

    override fun onStart() {
        super.onStart()
        mPresenter?.start()
    }

    private fun setRisk(risk: Int) {
        val riskView = listRisk?.get(risk - 1)

        val params = riskView?.layoutParams
        params?.height = 15.convertDpToPixel(context!!)
        riskView?.layoutParams = params
//        val margins = riskView?.layoutParams as ViewGroup.MarginLayoutParams
//        margins.setMargins(0, 10.convertDpToPixel(context!!), 2, 0)
//        riskView.layoutParams = margins
    }

    companion object {

        fun newInstance(): InvestFragment {
            return InvestFragment()
        }
    }
}
