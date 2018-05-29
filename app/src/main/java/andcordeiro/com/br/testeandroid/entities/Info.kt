package andcordeiro.com.br.testeandroid.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Info: Serializable{

    @SerializedName("name")
    var name: String? = null
    @SerializedName("data")
    var data: String? = null

    override fun toString(): String {
        return "Info(name=$name, data=$data)"
    }
}