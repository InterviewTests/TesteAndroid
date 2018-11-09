package br.com.gservices.santanderteste.pages.contacts.data.entities

import br.com.gservices.santanderteste.pages.contacts.data.entities.Contacts
import br.com.gservices.santanderteste.utils.ConvertableJSON
import com.google.gson.annotations.SerializedName

class ContactsResponse(@SerializedName("cells") var cells: List<Contacts>) : ConvertableJSON