package andcordeiro.com.br.testeandroid.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Year: Serializable {

    @SerializedName("fund")
    var fund: Double? = null
    @SerializedName("CDI")
    var cdi: Double? = null

    override fun toString(): String {
        return "Year(fund=$fund, cdi=$cdi)"
    }
}