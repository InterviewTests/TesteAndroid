package lucasonofre.santandertest.view.fragment


import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_investimento.*
import lucasonofre.santandertest.R
import lucasonofre.santandertest.adapter.InfoAdapter
import lucasonofre.santandertest.adapter.InfoDownAdapter
import lucasonofre.santandertest.adapter.InfoMoreAdapter
import lucasonofre.santandertest.model.*
import android.view.ViewGroup.MarginLayoutParams
import lucasonofre.santandertest.presenter.InvestimentoPresenter


/**
 * Interface dos métodos da View
 */
interface InvestimentoViewListener{

    fun setUpInfo(screenItens: ScreenItens)
    fun setRiskIndicator(risk: Int?)
    fun setListMoreInfo(items: ArrayList<YieldListItem>)
    fun setListInfo(items: ArrayList<Info>)
    fun setListDownInfo(items: ArrayList<DownInfo>)
    fun displayError(message: String)

}

class InvestimentoViewFragment : Fragment(), InvestimentoViewListener {

    private var layout      : ConstraintLayout?             = null
    private var infoList    : RecyclerView?                 = null
    private var btnInvestir : Button?                       = null
    private var moreInfolist: RecyclerView?                 = null
    private var infoDownList: RecyclerView?                 = null
    private var layoutRiscos:ConstraintLayout?              = null
    private var indicadorRisco:ImageView?                   = null

    // Instância do presenter
    private var presenter: InvestimentoPresenter = InvestimentoPresenter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view = inflater.inflate(R.layout.fragment_investimento, container, false)

        setViews(view)
        presenter.bindTo(this)
        presenter.requestFund(context!!)

        return view
    }

    /**
     * Destrói a referência ao presenter quando a view é destruida
     */
    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }

    /**
     * Recupera as listas e as views do layout e da progress view
     */

    private fun setViews(view: View) {
        infoList        = view.findViewById(R.id.list_info)
        moreInfolist    = view.findViewById(R.id.list_moreInfo)
        infoDownList    = view.findViewById(R.id.list_down_info)
        layout          = view.findViewById(R.id.container)
        btnInvestir     = view.findViewById(R.id.invest_btn)
        indicadorRisco  = view.findViewById(R.id.invest_risk_indicator)
        layoutRiscos    = view.findViewById(R.id.layout_view_risks)

        btnInvestir?.setOnClickListener { Toast.makeText(context,"Investimento realizado",Toast.LENGTH_SHORT).show() }
    }

    /**
     * Trata o risco recebido via parâmetro
     */

    override fun setRiskIndicator(risk: Int?) {
        risk?.let {
            setRiskViewSize(it)
        }
    }

    /**
     * Configura o indicador de risco conforme a chamada caso o mesmo não seja nulo
     */
    private fun setRiskViewSize(risk: Int){
        var indicators  = arrayListOf(invest_risk_1, invest_risk_2, invest_risk_3, invest_risk_4, invest_risk_5)
        var larguraLayoutTamanhos = layoutRiscos?.width?.minus(indicadorRisco!!.width)?.div(indicators.size)
        var posicaoSeta           = larguraLayoutTamanhos?.times(risk)?.minus(indicadorRisco!!.width)


        for (item in indicators) {
            if (risk == indicators.indexOf(item) + 1) {

                item.layoutParams.height            = item.layoutParams.height.times(2).minus(item.layoutParams.height.div(2))
                val marginParams = indicadorRisco?.layoutParams as MarginLayoutParams
                marginParams.setMargins(posicaoSeta!!, 0, 0, 0)
            }
        }
    }

    /**
     * Insere as informações vindas da chamada em seu respectivo campo
     */

    override fun setUpInfo(screenItens: ScreenItens) {
        invest_title     .text  = screenItens.title
        invest_investName.text  = screenItens.fundName
        invest_whatIs    .text  = screenItens.whatIs
        contact_item_text.text  = screenItens.definition
        invest_riskTitle .text  = screenItens.riskTitle
        invest_infoTitle .text  = screenItens.infoTitle
    }


    /**
     * Insere as informaçõs da lista moreInfo na tela
     */
    override fun setListMoreInfo(items: ArrayList<YieldListItem>) {
        moreInfolist?.layoutManager = LinearLayoutManager(context)
        moreInfolist?.adapter       = InfoMoreAdapter(activity!!, items)

    }

    /**
     * Insere as informaçõs da lista info na tela
     */
     override fun setListInfo(items: ArrayList<Info>) {
        infoList?.layoutManager = LinearLayoutManager(context)
        infoList?.adapter       = InfoAdapter(activity!!, items)
    }

    /**
     * Insere as informaçõs da lista DownInfo na tela
     */
    override fun setListDownInfo(items: ArrayList<DownInfo>) {
        infoDownList?.layoutManager = LinearLayoutManager(context)
        infoDownList?.adapter       = InfoDownAdapter(activity!!, items)
    }

    /**
     * Cria um toast com determinada mensagem passada por parâmetro
     */
    override fun displayError(message: String) {
        Toast.makeText(context!!, message, Toast.LENGTH_SHORT).show()
    }
}
