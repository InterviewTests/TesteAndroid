package br.com.santander.santanderinvestimento.investiment.data.entity

import br.com.santander.santanderinvestimento.investiment.domain.entity.Info
import br.com.santander.santanderinvestimento.util.JSONConvertable
import com.google.gson.annotations.SerializedName

class ScreenResponse(@SerializedName("title") val title: String,
                     @SerializedName("fundName") val fundName: String,
                     @SerializedName("whatIs") val whatIs: String,
                     @SerializedName("definition") val definition: String,
                     @SerializedName("riskTitle") val riskTitle: String,
                     @SerializedName("risk") val risk: Int,
                     @SerializedName("infoTitle") val infoTitle: String,
                     @SerializedName("info") val info: List<Info>,
                     @SerializedName("downInfo") val downInfo: List<Info>) : JSONConvertable