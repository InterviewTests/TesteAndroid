package com.vctapps.santanderchallenge.form.domain.cell

class TextFieldCell(id: Int = -1,
                    type: Int = 1,
                    message: String = "",
                    typeField: Int = 3,
                    hidden: Boolean = false,
                    topSpacing: Double = 8.0,
                    show: Int = -1,
                    required: Boolean = false) :
        FieldCell(id, type, message, typeField, hidden, topSpacing, show, required){

    override fun validateResponse(response: String) = response.isNotBlank()

}