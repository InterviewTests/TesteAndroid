package br.com.cesarsicas.stdandroidteste.mvp.home.investment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.cesarsicas.stdandroidteste.R
import br.com.cesarsicas.stdandroidteste.domains.InvestmentData
import kotlinx.android.synthetic.main.fragment_investment.*


/**
 * A simple [Fragment] subclass.
 */
class InvestmentFragment : Fragment(), InvestmentView {
    val presenter = InvestmentPresenter()

    override fun showError(message: String?) {

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                                     savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_investment, container, false)
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
        //presenter.getInvestmenentData()
        loadLocalData()
    }

    override fun setInvestmentData(data: InvestmentData) {
        title.text = data.screen?.title
        fundName.text = data.screen?.fundName
        whatIs.text = data.screen?.whatIs
        definition.text = data.screen?.definition
        riskTitle.text = data.screen?.riskTitle
        infoTitle.text = data.screen?.infoTitle


        monthFund.text = data?.screen?.moreInfo?.month?.fund
        monthCdi.text = data?.screen?.moreInfo?.month?.CDI

        yearFund.text = data?.screen?.moreInfo?.year?.fund
        yearCdi.text = data?.screen?.moreInfo?.year?.CDI

    }

    private fun loadLocalData(){
        var inputStream = getActivity().getAssets().open("investmentData.json")
        presenter.parseJsonData(inputStream)

    }


}
