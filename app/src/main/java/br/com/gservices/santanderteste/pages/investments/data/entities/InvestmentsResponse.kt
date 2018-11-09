package br.com.gservices.santanderteste.pages.investments.data.entities

import br.com.gservices.santanderteste.utils.ConvertableJSON
import com.google.gson.annotations.SerializedName

class InvestmentsResponse(@SerializedName("screen") val screen: Investments) : ConvertableJSON