package lucasonofre.santandertest.presenter

import android.content.Context
import android.util.Log
import lucasonofre.santandertest.fragment.ContatoViewListener
import lucasonofre.santandertest.model.Cell
import lucasonofre.santandertest.model.ContactItens
import lucasonofre.santandertest.request.RequestItens
import retrofit2.Call
import retrofit2.Response


interface ContatoPresenterListener{
    fun requestFormItems(context: Context)
    fun validateFields()
}

class ContatoPresenter: ContatoPresenterListener{

    private var view:ContatoViewListener? = null
    var items:ArrayList<ContactItens> = ArrayList()

    // Conecta a view da Activity ou Fragmento com a view do presenter
    fun bindTo(view:ContatoViewListener){
        this.view = view
    }

    // Destroi a referencia com a view da Activity ou Fragmento
    fun destroy(){
        this.view = null
    }

    override fun validateFields() {

        var isSucces = 0
        for (item in items){
//            if (item.typeField = TypeField.EMAIL || item.typeField = TypeField.TELNUMBER || item.typeField = TypeField.TEXT){
//                if (item.isSuccess){
//                    isSucces = isSucces+1
//                }
//            }
        }

        if(isSucces == 3){
            view?.showSuccessPage()
        }
        else{
            view?.displayError("Preencha todos os campos para continuar.")
        }
    }

    /**
     * Chamada que recebe a requisição da tela de investimentos
     */
    override fun requestFormItems(context: Context) {

        RequestItens(context).getCells().enqueue(object:retrofit2.Callback<Cell>{

            override fun onFailure(call: Call<Cell>, t: Throwable) {
                Log.i("Error", t.message)
            }

            override fun onResponse(call: Call<Cell>, response: Response<Cell>) {
                Log.i("Response", response.body().toString())

                response.body()?.contactItens?.let {
                    items = it
                    view?.updateRecyclerView(items)
                }?:run{
                    view?.displayError("Desculpe, houve um erro.")
                }
            }
        })
    }
}