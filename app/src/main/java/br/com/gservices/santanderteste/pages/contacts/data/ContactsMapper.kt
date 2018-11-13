package br.com.gservices.santanderteste.pages.contacts.data

import br.com.gservices.santanderteste.core.types.CommonTypes
import br.com.gservices.santanderteste.core.types.FieldTypes
import br.com.gservices.santanderteste.pages.contacts.data.entities.Contacts
import br.com.gservices.santanderteste.pages.contacts.data.entities.ContactsResponse

object ContactsMapper {
    fun map(cellJsonResponse: ContactsResponse): MutableList<Contacts> {
        val cellList = mutableListOf<Contacts>()
        cellJsonResponse.cells.mapTo(cellList) { cellResponse -> map(cellResponse) }
        return cellList
    }

    fun map(cellResponse: Contacts): Contacts {
        when (cellResponse.typeField) {
            1.0 -> cellResponse.typeField = FieldTypes.TEXT
            2.0 -> cellResponse.typeField = FieldTypes.TELNUMBER
            3.0 -> cellResponse.typeField = FieldTypes.EMAIL
            "telnumber" -> cellResponse.typeField = FieldTypes.TELNUMBER
            else -> cellResponse.typeField = FieldTypes.TEXT
        }

        when(cellResponse.type){
            1.0 -> cellResponse.type = CommonTypes.FIELD
            2.0 -> cellResponse.type = CommonTypes.TEXT
            3.0 -> cellResponse.type = CommonTypes.IMAGE
            4.0 -> cellResponse.type = CommonTypes.CHECKBOX
            5.0 -> cellResponse.type = CommonTypes.SEND
            else -> cellResponse.type = CommonTypes.TEXT
        }

        return cellResponse
    }
}