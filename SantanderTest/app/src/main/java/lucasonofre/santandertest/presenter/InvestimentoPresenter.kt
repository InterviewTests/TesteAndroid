package lucasonofre.santandertest.presenter

import android.content.Context
import android.util.Log
import lucasonofre.santandertest.view.fragment.InvestimentoViewListener
import lucasonofre.santandertest.model.Screen
import lucasonofre.santandertest.model.ScreenItens
import lucasonofre.santandertest.model.YieldListItem
import lucasonofre.santandertest.request.RequestItens
import retrofit2.Call
import retrofit2.Response


interface InvestimentoPresenterListene{
    fun requestFund(context: Context)
    fun moreInfoToArray()
    fun infoToArray()
    fun downInfoToArray()
    fun checkRiskIsValid()
}

class InvestimentoPresenter: InvestimentoPresenterListene{

    private var view: InvestimentoViewListener? = null
    private var screen:ScreenItens?   = null


    // Conecta a view da Activity ou Fragmento com a view do presenter
    fun bindTo(view:InvestimentoViewListener){
        this.view = view
    }

    // Destroi a referencia com a view da Activity ou Fragmento
    fun destroy(){
        this.view = null
    }


    /**
     * Chamada que recebe a requisição da tela de contatos
     */
    override fun requestFund(context: Context) {

        RequestItens(context).getFund().enqueue(object:retrofit2.Callback<Screen>{

            override fun onFailure(call: Call<Screen>, t: Throwable) {
                Log.i("Error", t.message)
            }

            override fun onResponse(call: Call<Screen>, response: Response<Screen>) {
                response.body()?.screen?.let {
                    screen = it

                    view?.setUpInfo(it)

                    checkRiskIsValid()
                    moreInfoToArray()
                    infoToArray()
                    downInfoToArray()

                }?:run {
                  view?.displayError("Ocorreu um erro")
                }
            }
        })
    }

    /**
     * Chamada que recebe a requisição da tela de contatos
     */
    override fun checkRiskIsValid() {
        screen?.risk?.let {
            view?.setRiskIndicator(it.toInt())
        }
    }

    /**
     * Chamada que recebe a requisição da tela de contatos
     */
    override fun moreInfoToArray() {
        screen?.moreInfo?.let {
            val arrayMoreInfo = arrayOf(
                YieldListItem(it.month,"No Mês"),
                YieldListItem(it.year,"No Ano"),
                YieldListItem(it.the12Months,"12 meses")
            )

            view?.setListMoreInfo(arrayMoreInfo.toCollection(ArrayList()))

        }?:run {

            view?.displayError("Ocorreu um erro")
        }
    }

    /**
     * Chamada que recebe a requisição da tela de contatos
     */
    override fun infoToArray() {
        screen?.info?.let {
            val arrayInfo = it
            view?.setListInfo(arrayInfo.toCollection(java.util.ArrayList()))

        }?:run {

            view?.displayError("Ocorreu um erro")
        }
    }

    /**
     * Chamada que recebe a requisição da tela de contatos
     */;
    override fun downInfoToArray() {
        screen?.downInfo?.let {
            val arrayInfo = it
            view?.setListDownInfo(arrayInfo.toCollection(java.util.ArrayList()))

        }?:run {

            view?.displayError("Ocorreu um erro")
        }
    }
}