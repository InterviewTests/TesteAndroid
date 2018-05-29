package andcordeiro.com.br.testeandroid.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ResultContact: Serializable {

    @SerializedName("cells")
    var cells: List<Cell>? = null

    override fun toString(): String {
        return "ResultContact(cells=$cells)"
    }
}