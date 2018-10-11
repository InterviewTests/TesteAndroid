package br.com.santander.santanderinvestimento.util

import java.text.DecimalFormat

class StringHelper {
    companion object {
        fun formatDoubleToString(valor: Double?): String {
            if (valor == null || valor == 0.0)
                return "0,00"
            var df = DecimalFormat("#.00")
            if (valor > 0.0 && valor < 1.0)
                df = DecimalFormat("0.00")
            return String.format("%s%% ", df.format(valor))
        }
    }
}