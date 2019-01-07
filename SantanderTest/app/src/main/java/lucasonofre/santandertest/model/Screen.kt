package lucasonofre.santandertest.model

import android.graphics.drawable.Drawable
import com.google.gson.annotations.SerializedName
import lucasonofre.santandertest.R.string.cdi
import lucasonofre.santandertest.custom.InfoDownButton
import java.time.Year


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
     val info: ArrayList<Info>? = null

     @SerializedName("downInfo")
     val downInfo: ArrayList<DownInfo>? = null
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
    var fund: Float? = null

    @SerializedName("CDI")
    var cdi: Float? = null


}

class Info {
    @SerializedName("name")
    val name: String? = null

    @SerializedName("data")
    val data: String? = null

}

class DownInfo {
    @SerializedName("name")
    val name: String? = null

    @SerializedName("data")
    val data: String? = null

}

class YieldListItem{

    var yield:Yield?      = null
    var titleItem:String? = null

    constructor(yield: Yield?, titleItem:String?) {
        this.yield = `yield`
        this.titleItem = titleItem

    }
}