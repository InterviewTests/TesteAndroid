package andcordeiro.com.br.testeandroid.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Screen:Serializable {

    @SerializedName("title")
    var title: String? = null
    @SerializedName("fundName")
    var fundName: String? = null
    @SerializedName("whatIs")
    var whatIs: String? = null
    @SerializedName("definition")
    var definition: String? = null
    @SerializedName("riskTitle")
    var riskTitle: String? = null
    @SerializedName("risk")
    var risk: Int? = null
    @SerializedName("infoTitle")
    var infoTitle: String? = null
    @SerializedName("moreInfo")
    var moreInfo: MoreInfo? = null
    @SerializedName("info")
    var info: List<Info>? = null
    @SerializedName("downInfo")
    var downInfo: List<DownInfo>? = null

    override fun toString(): String {
        return "Screen(title=$title, fundName=$fundName, whatIs=$whatIs, " +
                "definition=$definition, riskTitle=$riskTitle, risk=$risk, infoTitle=$infoTitle, " +
                "moreInfo=$moreInfo, info=$info, downInfo=$downInfo)"
    }
}