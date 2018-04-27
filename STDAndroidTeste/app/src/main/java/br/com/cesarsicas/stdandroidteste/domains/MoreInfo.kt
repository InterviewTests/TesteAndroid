package br.com.cesarsicas.stdandroidteste.domains

import com.google.gson.annotations.SerializedName

/**
 * Created by julio on 4/22/18.
 */

class MoreInfo {
    @SerializedName("12months")
    var twelveMonths: TwelveMonths? = null

    var month: Month? = null

    var year: Year? = null
}
