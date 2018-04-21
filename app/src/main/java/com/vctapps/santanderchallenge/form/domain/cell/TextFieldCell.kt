package com.vctapps.santanderchallenge.form.domain.cell

class TextFieldCell(id: Int? = -1,
                    type: Int? = 1,
                    message: String? = "",
                    typeField: Int? = 3,
                    hidden: Boolean? = false,
                    topSpacing: Float? = 8f,
                    show: Int? = -1,
                    required: Boolean? = false) :
        Cell(id, type, message, typeField, hidden, topSpacing, show, required){

    fun validateResponse(response: String) = response.isNotBlank()

}