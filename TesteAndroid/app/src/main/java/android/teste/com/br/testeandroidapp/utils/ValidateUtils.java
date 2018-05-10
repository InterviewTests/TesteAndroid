package android.teste.com.br.testeandroidapp.utils;

import android.content.Context;
import android.graphics.PorterDuff;
import android.teste.com.br.testeandroidapp.R;
import android.teste.com.br.testeandroidapp.entity.Cell;
import android.teste.com.br.testeandroidapp.entity.Type;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.List;

/**
 * Classe utilitária para validar campos
 *
 * @author Gabriela Santos
 */
public class ValidateUtils {

    /**
     * Valida os campos contidos no layout
     * @param context Contexto da activity
     * @param linearLayout Layout com os campos
     * @param cells Dados dos campos
     * @return True caso os campos estejam válidos, false caso contrário
     */
    public static Boolean validarCampos(Context context, LinearLayout linearLayout, List<Cell> cells) {
        Boolean formularioValido = true;

        EditText editText;
        String text;

        for (Cell cell : cells) {
            if (cell.getType() != Type.FIELD) {
                continue;
            }

            editText = (EditText) linearLayout.findViewWithTag(cell.getId());

            int visibility = linearLayout.findViewById(cell.getId()).getVisibility();
            if (visibility == View.INVISIBLE) {
                continue;
            }

            text = editText.getText().toString().trim();
            if (text.isEmpty() && !cell.getRequired()) {
                colorirEditText(context, editText, true);
                continue;
            }

            Boolean campoValido = true;

            switch (cell.getTypefield()) {
                case EMAIL:
                    campoValido = validarCampoEmail(text);
                    break;
                case TEXT:
                    campoValido = validarCampoTexto(text);
                    break;
                case TEL_NUMBER:
                    campoValido = validarCampoTelefone(text);
                    break;
            }

            colorirEditText(context, editText, campoValido);

            if (!campoValido) {
                formularioValido = false;
            }
        }

        return formularioValido;
    }

    /**
     * Verifica se o campo contém um telefone válido
     * @param telefone Campo com o telefone
     * @return True caso válido, false caso contrário
     */
    private static Boolean validarCampoTelefone(String telefone) {
        String number = telefone.trim();
        number = number.replace("-", "");
        number = number.replace("(", "");
        number = number.replace(")", "");
        number = number.replace(" ", "");

        try {
            Long.parseLong(number);
        }catch (Exception ex) {
            return false;
        }

        return  number.length() == 10 || number.length() == 11;
    }

    /**
     * Verifica se o campo está preenchido
     * @param texto Texto informado
     * @return True caso válido, false caso contrário
     */
    private static Boolean validarCampoTexto(String texto) {
        return !texto.trim().isEmpty();
    }

    /**
     * Valida o email informado
     * @param email Email informado
     * @return True caso válido, false caso contrário
     */
    private static Boolean validarCampoEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    /**
     * Colore a linha do EditText de verde caso seja válido ou
     * vermelho caso contrário
     * @param context Contexto
     * @param editText Campo a ser colorido
     * @param valid Boolean informando se ele é válido ou não
     */
    private static void colorirEditText(Context context, EditText editText, Boolean valid) {
        Integer color;

        if (valid) {
            color = R.color.fieldOk;
        } else {
            color = R.color.fieldError;
        }
        editText.getBackground().setColorFilter(
                context.getResources().getColor(color),
                PorterDuff.Mode.SRC_ATOP
        );
    }
}
