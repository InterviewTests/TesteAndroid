package br.com.santander.santanderinvestimento.contact.data

import br.com.santander.santanderinvestimento.contact.data.entity.Contact
import br.com.santander.santanderinvestimento.contact.data.entity.ContactResponse
import br.com.santander.santanderinvestimento.contact.domain.Type
import br.com.santander.santanderinvestimento.contact.domain.TypeField

object ContactMapper {
    fun map(cellJsonResponse: ContactResponse): MutableList<Contact> {
        val cellList = mutableListOf<Contact>()
        cellJsonResponse.cells.mapTo(cellList) { cellResponse -> map(cellResponse) }
        return cellList
    }

    fun map(cellResponse: Contact): Contact {
        when (cellResponse.typeField) {
            1.0 -> cellResponse.typeField = TypeField.TEXT
            2.0 -> cellResponse.typeField = TypeField.TELNUMBER
            3.0 -> cellResponse.typeField = TypeField.EMAIL
            "telnumber" -> cellResponse.typeField = TypeField.TELNUMBER
            else -> cellResponse.typeField = TypeField.TEXT
        }

        when(cellResponse.type){
            1.0 -> cellResponse.type = Type.FIELD
            2.0 -> cellResponse.type = Type.TEXT
            3.0 -> cellResponse.type = Type.IMAGE
            4.0 -> cellResponse.type = Type.CHECKBOX
            5.0 -> cellResponse.type = Type.SEND
            else -> cellResponse.type = Type.TEXT
        }

        return cellResponse
    }
}