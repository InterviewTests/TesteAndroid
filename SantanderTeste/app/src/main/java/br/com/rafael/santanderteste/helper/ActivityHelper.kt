package br.com.rafael.santanderteste.helper

import android.view.View
import android.widget.LinearLayout
import br.com.rafael.santanderteste.R

class ActivityHelper {
    companion object {
        @JvmStatic
        // Retorna a view do item risk de acordo com o id recebido da API
        fun getRiskItemView(riskNumber: Int, layout: LinearLayout): View {

            when(riskNumber) {
                1 -> return layout.findViewById(R.id.view1)
                2 -> return layout.findViewById(R.id.view2)
                3 -> return layout.findViewById(R.id.view3)
                4 -> return layout.findViewById(R.id.view4)
                else -> return layout.findViewById(R.id.view5)
            }
        }
    }
}