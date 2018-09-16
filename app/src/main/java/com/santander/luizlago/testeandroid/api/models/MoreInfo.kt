package com.santander.luizlago.testeandroid.api.models

import com.google.gson.annotations.SerializedName

data class MoreInfo(
        val month: MoreInfoValue,
        val year: MoreInfoValue,
        @SerializedName("12months")
        val twelveMonths: MoreInfoValue)