package com.vctapps.santanderchallenge.form.domain.cell

import com.vctapps.santanderchallenge.core.domain.StringPatterns
import java.util.regex.Pattern

class EmailFieldCell(id: Int = -1,
                     type: Int = 1,
                     message: String = "",
                     typeField: Int = 3,
                     hidden: Boolean = false,
                     topSpacing: Double = 8.0,
                     show: Int = -1,
                     required: Boolean = false) :
        FieldCell(id, type, message, typeField, hidden, topSpacing, show, required){

    private val EMAIL_PATTERN = Pattern.compile(StringPatterns.EMAIL_REGEX,
            Pattern.CASE_INSENSITIVE)

    override fun validateResponse(response: String) = EMAIL_PATTERN.matcher(response).find()

}