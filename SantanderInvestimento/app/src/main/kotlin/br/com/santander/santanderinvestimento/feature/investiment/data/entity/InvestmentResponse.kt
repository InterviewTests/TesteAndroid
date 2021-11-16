package br.com.santander.santanderinvestimento.feature.investiment.data.entity

import br.com.santander.santanderinvestimento.feature.investiment.domain.entity.Investment
import br.com.santander.santanderinvestimento.util.JSONConvertable
import com.google.gson.annotations.SerializedName

class InvestmentResponse(@SerializedName("screen") val screen: Investment) : JSONConvertable