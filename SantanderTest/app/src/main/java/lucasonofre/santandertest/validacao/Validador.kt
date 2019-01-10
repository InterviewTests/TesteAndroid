package lucasonofre.santandertest.validacao

import android.support.design.widget.TextInputLayout
import lucasonofre.santandertest.adapter.ContatoItemAdapter

class Validador{

    /**
     * Valida o Email recebido via parâmetro
     **/
     fun validaCampoVazio(viewHolder: ContatoItemAdapter.ViewHolderInputItem): Boolean {

        if (!viewHolder.editText.text.isNullOrEmpty()){
            removeErro(viewHolder.inputLayout)
            return false

        }else{
            viewHolder.inputLayout.error = "Favor, preencher o campo"
            return true
        }
    }

    /**
     * Valida o Email recebido via parâmetro
     **/
    fun validaEmail(viewHolder: ContatoItemAdapter.ViewHolderInputItem): Boolean {
        var email = viewHolder.editText.text.toString()

        if (viewHolder.editText.text.isNullOrEmpty()){
            viewHolder.inputLayout.error = "Favor, preencher o campo"
            return false

        }else if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            removeErro(viewHolder.inputLayout)
            return true

        }else{
            viewHolder.inputLayout.error = "Favor, preencher o campo"
            return false
        }
    }

    /**
     * Valida o telefone recebido via parâmetro
     **/
     fun validaTelefone(viewHolder: ContatoItemAdapter.ViewHolderInputItem): Boolean {

        var telefone = viewHolder.editText.text.toString()

        telefone
            .replace("(", "")
            .replace(")", "")
            .replace(" ", "")
            .replace("-", "")

        val digitos = telefone?.length

        if (digitos < 10 || digitos > 11){

            viewHolder.inputLayout.error = "Favor, preencher o campo com um telefone válido"
            return false

        }else{
            telefone.replace("([0-9]{2})([0-9]{4,5})([0-9]{4})", "($1) $2-$3")
            viewHolder.editText.setText(telefone)
            removeErro(viewHolder.inputLayout)
            return true
        }
    }

    /**
     * Remove erro do input recebido via parâmetro
     **/
     fun removeErro(inputLayout: TextInputLayout) {
        inputLayout.error          = null
        inputLayout.isErrorEnabled = false
    }

}