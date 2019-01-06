package lucasonofre.santandertest.model

import com.google.gson.annotations.SerializedName


 class Cell {

     @SerializedName("cells")
     var contactItens: ArrayList<ContactItens>? = ArrayList()

 }

class ContactItens{

    @SerializedName("id")
    val id: Int? = null

    @SerializedName("type")
    val type: Int? = null

    @SerializedName("message")
    val message: String? = null

    @SerializedName("typefield")
    val typeField: String? = null

    @SerializedName("hidden")
    val hidden: Boolean? = null

    @SerializedName("topSpacing")
    val topSpacing: Int? = null

    @SerializedName("show")
    val show: String? = null

    @SerializedName("required")
    val required: Boolean? = null
}