package com.vctapps.santanderchallenge.form.domain.cell

open class Cell(val id: Int = -1,
                val type: Int = 1,
                val message: String = "",
                val typeField: Int = 1,
                val hidden: Boolean = false,
                val topSpacing: Double = 8.0,
                val show: Int = -1,
                val required: Boolean = false){

    var response: Any? = null

    fun validateResponse(response: Any?): Boolean{
        return required && response != null
    }

}