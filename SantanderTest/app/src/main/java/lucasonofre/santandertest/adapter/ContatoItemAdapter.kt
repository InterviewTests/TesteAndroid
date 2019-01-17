package lucasonofre.santandertest.adapter

import android.app.Activity
import android.support.design.widget.TextInputLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.adapter_contact_text_item.view.*
import lucasonofre.santandertest.R
import lucasonofre.santandertest.model.ContactItens
import lucasonofre.santandertest.model.Type
import android.support.v7.widget.RecyclerView.ViewHolder
import android.text.InputType
import android.view.KeyEvent
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.adapter_contact_btn.view.*
import kotlinx.android.synthetic.main.adapter_contact_checkbox.view.*
import kotlinx.android.synthetic.main.adapter_contact_input_item.view.*
import lucasonofre.santandertest.validacao.Validador

/**
 * Interface para capturar a ação de click no botão
 **/

interface FragmentInterface{
    fun onButtonSelected(ehValido:Boolean)
}

class ContatoItemAdapter(private val context: Activity, private val itens: ArrayList<ContactItens>,private val listener:FragmentInterface): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // Variável de controle para os erros dos campos de Input
    private var ehValido:Boolean? = false


    private val TYPE_FIELD_TEXT        = 1.0
    private val TYPE_FIELD_EMAIL       = 3.0
    private val TYPE_FIELD_TEL_NUMBER  = "telnumber"

    private val TYPE_INPUT      = 1
    private val TYPE_TEXT       = 2
    private val TYPE_CHECK_BOX  = 4
    private val TYPE_BTN        = 5

    var validador: Validador? = Validador()

    /**
     * Filtra o tipo to Item e o torna um número
     **/
    override fun getItemViewType(position: Int): Int {

        val type = when(itens[position].type){

            Type.FIELD     -> 1
            Type.TEXT      -> 2
            Type.IMAGE     -> 3
            Type.CHECKBOX  -> 4
            Type.SEND      -> 5

            else -> -1
        }

        return type
    }

    /**
     * ViewHolder para os itens do tipo Texto
     **/
    class ViewHolderTextItem(itemView: View): RecyclerView.ViewHolder(itemView) {
        val layout  = itemView.contact_item_text_layout
        val textItem      = itemView.contact_item_text
    }

    /**
     * ViewHolder para os itens do tipo InputText
     **/
    class ViewHolderInputItem(itemView: View): RecyclerView.ViewHolder(itemView) {
        val layout      = itemView.contact_item_input_layout
        val inputLayout  = itemView.input_layout
        val editText    = itemView.input_layout_edit_text
    }

    /**
     * ViewHolder para os itens do tipo CheckBox
     **/
    class ViewHolderCheckBox(itemView: View): RecyclerView.ViewHolder(itemView) {
        val layout = itemView.contact_item_layout
        val checkBox     = itemView.contact_item_checkBox
        val textItem      = itemView.contact_item_check_text
    }

    /**
     * ViewHolder para os itens do tipo Botão
     **/
    class ViewHolderButton(itemView: View): RecyclerView.ViewHolder(itemView) {
        val layout = itemView.item_view_contact_btn_layout
        val button         = itemView.adapter_contact_btn_item

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        var viewHolder: ViewHolder?
        var view: View?

        //Infla o layout da view conforme o tipo do item
        when(viewType){

            TYPE_INPUT -> {

                view = LayoutInflater.from(context).inflate(R.layout.adapter_contact_input_item,parent,false)
                viewHolder = ViewHolderInputItem(view!!)

            }

            TYPE_TEXT -> {

                view = LayoutInflater.from(context).inflate(R.layout.adapter_contact_text_item,parent,false)
                viewHolder = ViewHolderTextItem(view!!)

            }


            TYPE_CHECK_BOX -> {

                view = LayoutInflater.from(context).inflate(R.layout.adapter_contact_checkbox,parent,false)
                viewHolder = ViewHolderCheckBox(view!!)

            }

            TYPE_BTN -> {

                view = LayoutInflater.from(context).inflate(R.layout.adapter_contact_btn,parent,false)
                viewHolder = ViewHolderButton(view!!)

            }

            else -> {
                view = LayoutInflater.from(context).inflate(null,parent,false)
                viewHolder = ViewHolderTextItem(view!!)}
        }

        return viewHolder

    }

    override fun getItemCount(): Int {
        return itens.size
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val item = itens[position]
        when(holder.itemViewType){

            /**
             * ViewHolder para os itens do tipo Text
             **/
            TYPE_TEXT -> {

                var viewHolder:ViewHolderTextItem = holder as ViewHolderTextItem
                viewHolder.textItem.text = item.message
                viewHolder.layout.setPadding(0,item.topSpacing!!,0,0)
            }

            /**
             * ViewHolder para os itens do tipo Input
             **/
            TYPE_INPUT -> {

                var viewHolder:ViewHolderInputItem = holder as ViewHolderInputItem
                viewHolder.inputLayout.hint = item.message
                viewHolder.layout.setPadding(0,item.topSpacing!!,0,0)


                //Filtra os tipos dos itens e os envia para o método correspondente
                when(item.typeField){

                    //Muda o tipo do teclado dependendo do tipo do item
                    TYPE_FIELD_EMAIL      -> viewHolder.editText.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS

                    TYPE_FIELD_TEL_NUMBER -> {

                        viewHolder.editText.inputType = InputType.TYPE_CLASS_NUMBER
                        configuraTecladoTelefone(viewHolder)
                    }


                    else -> viewHolder.editText.inputType = InputType.TYPE_TEXT_VARIATION_PERSON_NAME
                }


                viewHolder.editText.setOnFocusChangeListener { v, hasFocus -> if (!hasFocus){

                    //Filtra os tipos dos itens e os envia para o método correspondente
                    when(item.typeField){

                        TYPE_FIELD_TEXT       -> verificaCampoVazio(viewHolder)

                        TYPE_FIELD_EMAIL      -> verificaCampoEmail(viewHolder)

                        TYPE_FIELD_TEL_NUMBER -> verificaCampoTelefone(viewHolder)

                        else -> null
                    }
                   }
                }
            }

            /**
             * ViewHolder para os itens do tipo checkBox
             **/
            TYPE_CHECK_BOX -> {

                var viewHolder:ViewHolderCheckBox = holder as ViewHolderCheckBox
                viewHolder.textItem.text = item.message
                viewHolder.layout.setPadding(0,item.topSpacing!!,0,0)
            }

            /**
             * ViewHolder para os itens do tipo Botão
             **/
            TYPE_BTN -> {

                var viewHolder:ViewHolderButton = holder as ViewHolderButton
                viewHolder.button.text = item.message
                viewHolder.layout.setPadding(0,item.topSpacing!!,0,20)


                viewHolder.button.setOnClickListener {

                    if (ehValido == true) {
                        listener.onButtonSelected(ehValido!!)
                    }else{
                        Toast.makeText(context,"Favor verifique os campos preenchidos",Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun configuraTecladoTelefone(viewHolder: ViewHolderInputItem) {
        viewHolder.editText.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if ((event?.action == KeyEvent.ACTION_DOWN) && (actionId == KeyEvent.KEYCODE_ENTER)) {

                return@OnEditorActionListener true
            }
            false
        })
    }

    /**
     * Instância da classe que valida os campos e retorna um boolean de acordo com a validação
     **/
    private fun verificaCampoVazio(viewHolder: ViewHolderInputItem) {

        val campo = viewHolder.editText.text.toString()

        if (!validador?.validaCampoVazio(campo)!!) {
            ehValido = true
            removeErro(viewHolder.inputLayout)

        } else {
            ehValido = false
            aplicaErro(viewHolder)
        }
    }


    /**
     * Instância da classe que valida os campos e retorna um boolean de acordo com a validação
     **/
    private fun verificaCampoEmail(viewHolder: ViewHolderInputItem) {

        val email = viewHolder.editText.text.toString()

        if (!email.isEmpty()){

            if (validador?.validaEmail(email)!!) {
                ehValido = true
                removeErro(viewHolder.inputLayout)

            }else {
                ehValido = false
                aplicaErro(viewHolder)
            }
        }else {
            ehValido = false
            aplicaErro(viewHolder)
        }
    }


    /**
     * Instância da classe que valida os campos e retorna um boolean de acordo com a validação
     **/
    private fun verificaCampoTelefone(viewHolder: ViewHolderInputItem) {

        val telefone = viewHolder.editText.text.toString()

        if (validador?.validaTelefone(telefone)!!) {
            ehValido = true
            removeErro(viewHolder.inputLayout)
        } else {
            ehValido = false
            aplicaErroTelefone(viewHolder)
        }
    }

    private fun aplicaErroTelefone(viewHolder: ContatoItemAdapter.ViewHolderInputItem) {

        viewHolder.inputLayout.error = "Favor, preencher o campo com um telefone válido"
    }

    /**
     * Remove erro do input recebido via parâmetro
     **/
    private fun removeErro(inputLayout: TextInputLayout) {
        inputLayout.error          = null
        inputLayout.isErrorEnabled = false
    }

    /**
     * Aplica o erro ao view Holder
     **/
    private fun aplicaErro(viewHolder: ViewHolderInputItem) {
        viewHolder.inputLayout.error = "Favor, preencher o campo"
    }
}