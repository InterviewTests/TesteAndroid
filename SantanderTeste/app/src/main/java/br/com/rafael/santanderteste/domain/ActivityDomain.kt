package br.com.rafael.santanderteste.domain

import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import br.com.rafael.santanderteste.R

class ActivityDomain {
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

        @JvmStatic
        // Retorna a view do item risk de acordo com o id recebido da API
        fun getFormContactItemView(view_type: Int, inflater: LayoutInflater): View {

            val layout = inflater.inflate(R.layout.risk_layout, null, false)

            when(view_type) {
                1 -> return inflater.inflate(R.layout.input_layout, null, false)
                2 -> return inflater.inflate(R.layout.text_view_layout, null, false)
                3 -> return inflater.inflate(R.layout.image_view_layout, null, false)
                4 -> return inflater.inflate(R.layout.check_box_layout, null, false)
                else -> return inflater.inflate(R.layout.button_layout, null, false)
            }
        }
    }
}