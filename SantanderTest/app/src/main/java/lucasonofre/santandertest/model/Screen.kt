package lucasonofre.santandertest.model

import com.google.gson.annotations.SerializedName


class Screen{
    @SerializedName("screen")
    var screen: ScreenItens? = null
}

 class ScreenItens {

     @SerializedName("title")
     val title:  String? = null

     @SerializedName("fundName")
     val fundName: String? = null

     @SerializedName("whatIs")
     val whatIs: String? = null

     @SerializedName("definition")
     val definition: String? = null

     @SerializedName("riskTitle")
     val riskTitle: String? = null

     @SerializedName("risk")
     val risk: Long? = null

     @SerializedName("infoTitle")
     val infoTitle: String? = null

     @SerializedName("moreInfo")
     val moreInfo: MoreInfo? = null

     @SerializedName("info")
     val info: List<Info>? = null

     @SerializedName("downInfo")
     val downInfo: List<Info>? = null
 }
 class MoreInfo {
     @SerializedName("month")
     val month: Yield? = null

     @SerializedName("year")
     val year: Yield? = null

     @SerializedName("12months")
     val the12Months: Yield? = null
 }

 class Yield {
    @SerializedName("fund")
    val fund: Float? = null

    @SerializedName("CDI")
    val cdi: Float? = null
}

class Info {
    @SerializedName("name")
    val name: String? = null

    @SerializedName("data")
    val data: String? = null

}