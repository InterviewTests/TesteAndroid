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
import android.widget.Toast
import kotlinx.android.synthetic.main.adapter_contact_btn.view.*
import kotlinx.android.synthetic.main.adapter_contact_checkbox.view.*
import kotlinx.android.synthetic.main.adapter_contact_input_item.view.*


interface FragmentInterface{
    fun onFragmentSelected()
}


class ContatoItemAdapter(private val context: Activity, private val itens: ArrayList<ContactItens>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    var ehValido:Boolean? = false

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

    class ViewHolderTextItem(itemView: View): RecyclerView.ViewHolder(itemView) {

        val layout = itemView.contact_item_text_layout
        val textItem     = itemView.contact_item_text
    }

    class ViewHolderInputItem(itemView: View): RecyclerView.ViewHolder(itemView) {

        val layout     = itemView.contact_item_input_layout
        val inputLayout = itemView.input_layout
        val editText   = itemView.input_layout_edit_text
    }

    class ViewHolderCheckBox(itemView: View): RecyclerView.ViewHolder(itemView) {


        val layout = itemView.contact_item_layout
        val checkBox     = itemView.contact_item_checkBox
        val textItem     = itemView.contact_item_check_text
    }

    class ViewHolderButton(itemView: View): RecyclerView.ViewHolder(itemView) {

        val layout = itemView.item_view_contact_btn_layout
        val button = itemView.adapter_contact_btn_item

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        var viewHolder: ViewHolder?
        var view: View? = null

        when(viewType){

            1 -> {

                view = LayoutInflater.from(context).inflate(R.layout.adapter_contact_input_item,parent,false)
                viewHolder = ViewHolderInputItem(view!!)

            }

            2 -> {

                view = LayoutInflater.from(context).inflate(R.layout.adapter_contact_text_item,parent,false)
                viewHolder = ViewHolderTextItem(view!!)

            }


            4 -> {

                view = LayoutInflater.from(context).inflate(R.layout.adapter_contact_checkbox,parent,false)
                viewHolder = ViewHolderCheckBox(view!!)

            }

            5 -> {

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

            2 -> {

                var viewHolder:ViewHolderTextItem = holder as ViewHolderTextItem
                viewHolder.textItem.text = item.message
                viewHolder.layout.setPadding(0,item.topSpacing!!,0,0)
            }

            1 -> {

                var viewHolder:ViewHolderInputItem = holder as ViewHolderInputItem
                viewHolder.editText.hint = item.message

                viewHolder.layout.setPadding(0,item.topSpacing!!,0,0)

                viewHolder.editText.setOnFocusChangeListener { v, hasFocus -> if (!hasFocus){

                    when(item.typeField){

                        1.0 -> validaNome(viewHolder)

                        3.0 -> validaEmail(viewHolder)

                        "telnumber" -> validaTelefone(viewHolder)

                        else -> null
                    }

                   }
                }

            }

            4 -> {

                var viewHolder:ViewHolderCheckBox = holder as ViewHolderCheckBox
                viewHolder.textItem.text = item.message

                viewHolder.layout.setPadding(0,item.topSpacing!!,0,0)
            }

            5 -> {

                var viewHolder:ViewHolderButton = holder as ViewHolderButton
                viewHolder.button.text = item.message
                viewHolder.layout.setPadding(0,item.topSpacing!!,0,20)


                viewHolder.button.setOnClickListener {

                    if (ehValido!!)
                        Toast.makeText(context,"Mensagem enviada",Toast.LENGTH_SHORT).show()
                    else
                        Toast.makeText(context,"Favor, validar se os campos estão preenchidos corretamente",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    private fun validaNome(viewHolder: ContatoItemAdapter.ViewHolderInputItem) {

            if (!viewHolder.editText.text.isNullOrEmpty()){
                ehValido  = false
                removeErro(viewHolder.inputLayout)
            }else{
            viewHolder.inputLayout.error = "Favor, preencher o campo"
            ehValido  = true
        }
    }

    private fun validaEmail(viewHolder: ContatoItemAdapter.ViewHolderInputItem) {
        var email = viewHolder.editText.text.toString()

        if (viewHolder.editText.text.isNullOrEmpty()){
            viewHolder.inputLayout.error = "Favor, preencher o campo"
            ehValido  = false

        }else if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                ehValido  = true
                removeErro(viewHolder.inputLayout)
            }else{
                ehValido  = false
                viewHolder.inputLayout.error = "Favor, preencher o campo"
            }
         }


    fun validaTelefone(viewHolder: ContatoItemAdapter.ViewHolderInputItem) {


            var telefone = viewHolder.editText.text.toString()

            telefone
                .replace("(", "")
                .replace(")", "")
                .replace(" ", "")
                .replace("-", "")

            val digitos = telefone?.length

            if (digitos < 10 || digitos > 11){

                viewHolder.inputLayout.error = "Favor, preencher o campo com um telefone válido"
                ehValido  = false

            }else{
                telefone.replace("([0-9]{2})([0-9]{4,5})([0-9]{4})", "($1) $2-$3")
                viewHolder.editText.setText(telefone)
                ehValido  = true
                removeErro(viewHolder.inputLayout)
            }
         }

    private fun removeErro(inputLayout: TextInputLayout) {
        inputLayout.error          = null
        inputLayout.isErrorEnabled = false
    }
}