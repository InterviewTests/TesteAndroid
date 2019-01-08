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


//    fun convertToTypeField(){
//
//        when(typeField){
//
//            1 ->TypeField.TEXT
//            2 ->TypeField.TELNUMBER
//            3 ->TypeField.EMAIL
//
//            else -> null
//        }
//    }
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