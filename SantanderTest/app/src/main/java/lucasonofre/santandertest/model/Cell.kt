package lucasonofre.santandertest.model

import com.google.gson.annotations.SerializedName


/**
 * Classe modelo do Item da chamada
 */
class Cell {

    @SerializedName("cells")
    var contactItens: ArrayList<ContactItens>? = ArrayList()

}

/**
 * Classe modelo de cada item da chamada
 */
class ContactItens{

    @SerializedName("id")
    val id: Int? = null

    @SerializedName("type")
    val type: Type? = null

    @SerializedName("message")
    val message: String? = null

    @SerializedName("typefield")
    val typeField: Any? = null

    @SerializedName("hidden")
    val hidden: Boolean? = null

    @SerializedName("topSpacing")
    val topSpacing: Int? = null

    @SerializedName("show")
    val show: String? = null

    @SerializedName("required")
    val required: Boolean? = null

}

enum class Type{

    @SerializedName("1")
    FIELD,

    @SerializedName("2")
    TEXT ,

    @SerializedName("3")
    IMAGE,

    @SerializedName("4")
    CHECKBOX,

    @SerializedName("5")
    SEND
}

enum class TypeField {
    TEXT,
    TELNUMBER,
    EMAIL
}