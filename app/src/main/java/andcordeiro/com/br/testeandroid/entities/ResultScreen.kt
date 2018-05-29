package andcordeiro.com.br.testeandroid.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ResultScreen: Serializable {

    @SerializedName("screen")
    var screen: Screen? = null

    override fun toString(): String {
        return "ResultScreen(screen=$screen)"
    }
}