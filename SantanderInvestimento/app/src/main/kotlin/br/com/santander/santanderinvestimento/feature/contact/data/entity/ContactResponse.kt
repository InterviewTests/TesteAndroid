package br.com.santander.santanderinvestimento.feature.contact.data.entity

import br.com.santander.santanderinvestimento.util.JSONConvertable
import com.google.gson.annotations.SerializedName

class ContactResponse(@SerializedName("cells") var cells: List<Contact>?) : JSONConvertable