package br.com.rafael.santanderteste.domain;

data class Cell (
    var id: Int?,
    var type: Int?,
    var message: String?,
    var typefield: Int?,
    var hidden: Boolean,
    var topSpacing: Int,
    var show: Boolean,
    var required: Boolean
)
