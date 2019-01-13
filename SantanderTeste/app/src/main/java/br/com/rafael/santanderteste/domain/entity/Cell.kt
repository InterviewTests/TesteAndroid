package br.com.rafael.santanderteste.domain.entity;

//Entidade que representa uma configuracao de celula (cell)
data class Cell (
    var id: Int?,
    var type: Int?,
    var message: String?,
    var typefield: String?,
    var hidden: Boolean?,
    var topSpacing: Int?,
    var show: Int?,
    var required: Boolean?
)
