package br.com.santander.santanderinvestimento.contact.data.entity

import br.com.santander.santanderinvestimento.util.JSONConvertable
import com.google.gson.annotations.SerializedName

class ContactResponse(@SerializedName("cells") var cells: List<Contact>) : JSONConvertable