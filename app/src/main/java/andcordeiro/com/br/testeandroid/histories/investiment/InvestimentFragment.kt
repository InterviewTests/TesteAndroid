package andcordeiro.com.br.testeandroid.histories.investiment

import andcordeiro.com.br.testeandroid.R
import andcordeiro.com.br.testeandroid.entities.ResultScreen
import andcordeiro.com.br.testeandroid.system.dagger.App
import andcordeiro.com.br.testeandroid.system.extensions.hide
import andcordeiro.com.br.testeandroid.system.extensions.show
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_investiment.*
import javax.inject.Inject


class InvestimentFragment : Fragment(), InvestimentMVP.View {

    companion object {
        fun newInstance(): InvestimentFragment = InvestimentFragment()
    }

    @Inject
    internal lateinit var presenter: InvestimentMVP.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity!!.application as App).getComponent().inject(this)

        presenter.loadScreen()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_investiment, container, false)

    override fun onStart() {
        super.onStart()
        presenter.setView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.rxUnsubscribe()
    }

    override fun shortShowMessage(msg: String?) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }

    override fun context(): Context = activity!!.baseContext

    override fun setComponents(result: ResultScreen) {
        title.text = result.screen!!.title
        fundName.text = result.screen!!.fundName
        whatIs.text = result.screen!!.whatIs
        definition.text = result.screen!!.definition
        riskTitle.text = result.screen!!.riskTitle
        defaultRisk()
        setRisk(result.screen!!.risk!!)
        infoTitle.text = result.screen!!.infoTitle
        monthFund.text = getString(R.string.percent, result.screen!!.moreInfo!!.month!!.fund.toString())
        monthCdi.text = getString(R.string.percent, result.screen!!.moreInfo!!.month!!.cdi.toString())
        yearFund.text = getString(R.string.percent, result.screen!!.moreInfo!!.year!!.fund.toString())
        yearCdi.text = getString(R.string.percent, result.screen!!.moreInfo!!.year!!.cdi.toString())
        monthsFund.text = getString(R.string.percent, result.screen!!.moreInfo!!.twelveMonths!!.fund.toString())
        monthsCdi.text = getString(R.string.percent, result.screen!!.moreInfo!!.twelveMonths!!.cdi.toString())
        infoName1.text = result.screen!!.info!![0].name
        infoData1.text = result.screen!!.info!![0].data
        infoName2.text = result.screen!!.info!![1].name
        infoData2.text = result.screen!!.info!![1].data
        infoName3.text = result.screen!!.info!![2].name
        infoData3.text = result.screen!!.info!![2].data
        infoName4.text = result.screen!!.info!![3].name
        infoData4.text = result.screen!!.info!![3].data
        infoName5.text = result.screen!!.info!![4].name
        infoData5.text = result.screen!!.info!![4].data
        infoName6.text = result.screen!!.info!![5].name
        infoData6.text = result.screen!!.info!![5].data
        infoName7.text = result.screen!!.info!![6].name
        infoData7.text = result.screen!!.info!![6].data
        downInfoName1.text = result.screen!!.downInfo!![0].name
        downInfoName2.text = result.screen!!.downInfo!![1].name
        downInfoName3.text = result.screen!!.downInfo!![2].name
        downInfoName4.text = result.screen!!.downInfo!![3].name
        downInfoName5.text = result.screen!!.downInfo!![4].name
        constraintInvest.show()
    }

    override fun setRisk(risk: Int) {
        when (risk){
            1 -> {arrowBtn1.show(); btnRisk1.layoutParams.height = 22}
            2 -> {arrowBtn2.show(); btnRisk2.layoutParams.height = 22}
            3 -> {arrowBtn3.show(); btnRisk3.layoutParams.height = 22}
            4 -> {arrowBtn4.show(); btnRisk4.layoutParams.height = 22}
            5 -> {arrowBtn5.show(); btnRisk5.layoutParams.height = 22}
        }
    }

    override fun defaultRisk(){
        btnRisk1.layoutParams.height = 18
        btnRisk2.layoutParams.height = 18
        btnRisk3.layoutParams.height = 18
        btnRisk4.layoutParams.height = 18
        btnRisk5.layoutParams.height = 18
        arrowBtn1.hide()
        arrowBtn2.hide()
        arrowBtn3.hide()
        arrowBtn4.hide()
        arrowBtn5.hide()
    }
}
