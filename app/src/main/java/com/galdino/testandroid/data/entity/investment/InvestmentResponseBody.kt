package com.galdino.testandroid.data.entity.investment

import com.google.gson.annotations.SerializedName

data class InvestmentResponseBody (@SerializedName("screen")
                              private val screen: ScreenInvestment? = null)