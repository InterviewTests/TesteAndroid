package andcordeiro.com.br.testeandroid.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class DownInfo:Serializable {

    @SerializedName("name")
    var name: String? = null
    @SerializedName("data")
    var data: String? = null

    override fun toString(): String {
        return "DownInfo(name=$name, data=$data)"
    }
}