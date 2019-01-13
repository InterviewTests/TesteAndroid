package br.com.rafael.santanderteste.domain.entity

// Entidade que será parseada a partir do retorno do Json de lista de cells
data class CellCatalog (
    var cells: List<Cell>
)