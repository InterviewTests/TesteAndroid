package br.com.santander.santanderinvestimento.investiment.domain.entity

import com.google.gson.annotations.SerializedName


data class TimeInfo(@SerializedName("month") val month: MoreInfo?,
                    @SerializedName("year") val year: MoreInfo?,
                    @SerializedName("12months") val months12: MoreInfo?)