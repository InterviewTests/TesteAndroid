package br.com.rafael.santanderteste.helper

import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.*
import br.com.rafael.santanderteste.R
import br.com.rafael.santanderteste.domain.entity.Cell
import br.com.rafael.santanderteste.presentation.fragment.FundFragment
import java.util.regex.Matcher
import java.util.regex.Pattern

class ViewHelper {

    companion object {
        /**
         * Atualiza um Fragment que ja esta adicionado em um layout view por outro
         * Verifica se o fragment esta nulo para criar ou apenas exibir
         * @param supportFragmentManager FragmentManager
         * @param layoutReference O id do layout que servira de container para o Fragment
         * @param fragment Fragment que sera adicionado/atualizado no container de layout
         */
        fun replace_framgment(supportFragmentManager: FragmentManager, layoutReference: Int, fragment: Fragment, tag : String) {
            if(tag.equals("invest")) {
                if(supportFragmentManager.findFragmentByTag("invest") != null) {
                    supportFragmentManager.beginTransaction().show(supportFragmentManager.findFragmentByTag("invest")).commit();
                } else {
                    supportFragmentManager.beginTransaction().add(layoutReference, fragment, "invest").commit();
                }
                if(supportFragmentManager.findFragmentByTag("contact") != null) {
                    supportFragmentManager.beginTransaction().hide(supportFragmentManager.findFragmentByTag("contact")).commit();
                }
            } else {
                if(supportFragmentManager.findFragmentByTag("contact") != null) {
                    supportFragmentManager.beginTransaction().show(supportFragmentManager.findFragmentByTag("contact")).commit();
                } else {
                    supportFragmentManager.beginTransaction().add(layoutReference, fragment, "contact").commit();
                }
                if(supportFragmentManager.findFragmentByTag("invest") != null) {
                    supportFragmentManager.beginTransaction().hide(supportFragmentManager.findFragmentByTag("invest")).commit();
                }
            }

        }

        /**
         * @param value Um valor String ou nao que represente o tipo de input vindo da api
         */
        fun get_input_type(value: String): Int {
            when(isString(value)) {
                true ->
                    if (value.equals("telnumber")) {
                        return InputType.TYPE_CLASS_PHONE
                    } else {
                        return InputType.TYPE_CLASS_TEXT
                    }
                false ->
                    if (value.toInt() == 2) {
                        return InputType.TYPE_CLASS_PHONE
                    } else if (value.toInt() == 3){
                        return InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                    } else {
                        return InputType.TYPE_CLASS_TEXT
                    }
            }
        }

        /**
         * Verifica se um valor de String é convertivel em Inteiro
         */
        private fun isString(str: String): Boolean {
            val v = str.toIntOrNull()
            return when(v) {
                null -> true
                else -> false
            }
        }

        /**
         * Verifica se um email e valido
         * @param email String
         */
        fun isEmailValid(email: String) : Boolean{
            var EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
            var pattern = Pattern.compile(EMAIL_PATTERN);
            var matcher: Matcher;

            matcher = pattern.matcher(email);
            return matcher.matches();
        }
    }
}