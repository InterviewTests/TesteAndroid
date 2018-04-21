package com.vctapps.santanderchallenge.form.domain.cell

import com.vctapps.santanderchallenge.core.domain.StringPatterns
import java.util.regex.Pattern

class TelNumberFieldCell(id: Int? = -1,
                         type: Int? = 1,
                         message: String? = "",
                         typeField: Int? = 2,
                         hidden: Boolean? = false,
                         topSpacing: Float? = 8f,
                         show: Int? = -1,
                         required: Boolean? = false) :
        Cell(id, type, message, typeField, hidden, topSpacing, show, required){

    private val PHONE_NUMBER_PATTERN = Pattern
            .compile(StringPatterns.PHONE_NUMBER_REGEX,
            Pattern.CASE_INSENSITIVE)

    fun validateResponse(response: String) =
            PHONE_NUMBER_PATTERN.matcher(response).find()

}