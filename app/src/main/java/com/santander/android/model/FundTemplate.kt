package com.santander.android.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FundTemplate(
        var screen: FundTemplate.Screen? = null
): Serializable {

    data class Screen(
            var title: String? = null,
            var fundName: String? = null,
            var whatIs: String? = null,
            var definition: String? = null,
            var riskTitle: String? = null,
            var risk: Int? = null,
            var infoTitle: String? = null,
            var moreInfo: Investment? = null,
            var info: List<Info> = ArrayList(),
            var downInfo: List<Info> = ArrayList()
    ) : Serializable

    data class Investment(
            var month: Rate? = null,
            var year: Rate? = null,
            @SerializedName(value = "12months")
            var twelveMonths: Rate? = null
    ) : Serializable

    data class Rate(
            var fund: Double? = null,
            var CDI: Double? = null
    ) : Serializable

    data class Info(
            var name: String? = null,
            var data: String? = null
    ) : Serializable

}