package lucasonofre.santandertest.validacao

class Validador{

    /**
     * Valida o Email recebido via parâmetro
     **/
     fun validaCampoVazio(campo: String): Boolean {
        return campo.isEmpty()
    }

    /**
     * Valida o Email recebido via parâmetro
     **/
    fun validaEmail(email: String): Boolean {

         if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            return true
        }else{
            return false
        }
    }

    /**
     * Valida o telefone recebido via parâmetro
     **/
     fun validaTelefone(telefone: String): Boolean {

        formataTelefone(telefone)

        val digitos = telefone?.length

        return validaDigitos(digitos)
    }

    /**
     * Valida os digitos apresentados
     **/
     fun validaDigitos(digitos: Int): Boolean {
        if (digitos < 10 || digitos > 11) {

            return false

        } else {
            //telefone.replace("([0-9]{2})([0-9]{4,5})([0-9]{4})", "($1) $2-$3")
            //viewHolder.editText.setText(telefone)
            return true
        }
    }

    private fun formataTelefone(telefone: String) {
        telefone
            .replace("(", "")
            .replace(")", "")
            .replace(" ", "")
            .replace("-", "")
    }


}