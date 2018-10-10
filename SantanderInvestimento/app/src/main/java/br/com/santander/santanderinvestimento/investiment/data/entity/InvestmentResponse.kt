package br.com.santander.santanderinvestimento.investiment.data.entity

import br.com.santander.santanderinvestimento.util.JSONConvertable
import com.google.gson.annotations.SerializedName


class InvestmentResponse(@SerializedName("screen") val screen: ScreenResponse) : JSONConvertable