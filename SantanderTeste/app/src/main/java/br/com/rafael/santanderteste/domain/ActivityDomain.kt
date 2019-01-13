package br.com.rafael.santanderteste.domain

import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import br.com.rafael.santanderteste.R
import br.com.rafael.santanderteste.domain.entity.Cell

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
        fun getFormContactItemView(cell: Cell, inflater: LayoutInflater): View {
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            val visible = if (cell.hidden == true) View.GONE else View.VISIBLE
            val marginTop = cell.topSpacing
            params.setMargins(0, marginTop!!, 0, 0)

            var viewReturned = when(cell.type) {
                1 -> inflater.inflate(R.layout.input_layout, null, false)
                2 -> inflater.inflate(R.layout.text_view_layout, null, false)
                3 -> inflater.inflate(R.layout.image_view_layout, null, false)
                4 -> inflater.inflate(R.layout.check_box_layout, null, false)
                else -> inflater.inflate(R.layout.button_layout, null, false)
            }

            viewReturned.setVisibility(visible)
            viewReturned.setLayoutParams(params)
            return viewReturned
        }
    }
}