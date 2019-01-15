package com.luccasmelo.santandereveris.data.model

import com.google.gson.annotations.SerializedName

class ContactForm{
    var cells : ArrayList<Cells>? = null

class Cells{
    var id:Int? = null
    var type:Type? = null
    var typefield:TypeField? = null
    var message:String? = null
    var hidden:Boolean? = null
    var topSpacing:Int? = null
    var show:Int? = null
    var required:Boolean? = null
}
}


enum class Type(val value: Int) {
    @SerializedName("1")
    field(1),
    @SerializedName("2")
    text(2),
    @SerializedName("3")
    image(3),
    @SerializedName("4")
    checkbox(4),
    @SerializedName("5")
    send(5);


}


enum class TypeField(value: Int) {
    @SerializedName("1")
    text(1),
    @SerializedName("telnumber")
    telNumber(2),
    @SerializedName("3")
    email(3)
}