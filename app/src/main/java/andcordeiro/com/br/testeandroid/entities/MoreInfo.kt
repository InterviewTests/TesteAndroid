package andcordeiro.com.br.testeandroid.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class MoreInfo: Serializable {

    @SerializedName("month")
    var month: Month? = null
    @SerializedName("year")
    var year: Year? = null
    @SerializedName("12months")
    var twelveMonths: TwelveMonths? = null

    override fun toString(): String {
        return "MoreInfo(month=$month, year=$year, twelveMonths=$twelveMonths)"
    }
}