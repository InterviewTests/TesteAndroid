package com.luccasmelo.santandereveris.data.model

import com.google.gson.annotations.SerializedName
import com.luccasmelo.santandereveris.util.toPercent

class InvestmentInformation{
var screen : Screen? =  null
    class Screen{
        var title:String? = null
        var fundName:String? = null
        var whatIs:String? = null
        var definition:String? = null
        var riskTitle:String? = null
        var risk:Int? = null
        var infoTitle:String? = null
        var moreInfo:MoreInfo? = null
        var info:ArrayList<Info>? = null
        var downInfo:ArrayList<Info>? = null

        class MoreInfo{
            var month:DataInfo? = null
            var year:DataInfo? = null
            @SerializedName("12months")
            var months12:DataInfo? = null

            open class DataInfo{
                var fund:String? = null
                var cdi:String? = null

                var fundPercent:String? = null
                    get() = fund?.toPercent()
                var cdiPercent:String? = null
                    get() = fund?.toPercent()

            }
        }

        class Info{
            var name:String? = null
            var data:String? = null

        }

    }
}

class DataInfoField(val field: FieldDataInfoEnum, dataInfo: InvestmentInformation.Screen.MoreInfo.DataInfo): InvestmentInformation.Screen.MoreInfo.DataInfo() {
init {
    fund = dataInfo.fund
    cdi = dataInfo.cdi
}

    enum class FieldDataInfoEnum{
        MONTH,
        YEAR,
        MONTH_12;

        override fun toString(): String {
            return when(this){
                MONTH -> "No mês"
                    YEAR -> "No ano"
                MONTH_12 -> "12 meses"

            }
        }


    }
}


