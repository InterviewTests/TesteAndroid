package andcordeiro.com.br.testeandroid.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Cell: Serializable {

    @SerializedName("id")
    var id: Int? = null
    @SerializedName("type")
    var type: Int? = null
    @SerializedName("message")
    var message: String? = null
    @SerializedName("typefield")
    var typeField: String? = null
    @SerializedName("hidden")
    var hidden: Boolean? = null
    @SerializedName("topSpacing")
    var topSpacing: Double? = null
    @SerializedName("show")
    var show: Int? = null
    @SerializedName("required")
    var required: Boolean? = null

    override fun toString(): String {
        return "Cell(id=$id, type=$type, typeField=$typeField, " +
                "hidden=$hidden, topSpacing=$topSpacing, show=$show, required=$required)"
    }

}