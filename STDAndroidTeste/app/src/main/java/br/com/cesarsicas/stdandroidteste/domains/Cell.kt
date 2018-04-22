package br.com.cesarsicas.stdandroidteste.domains

import br.com.cesarsicas.stdandroidteste.constants.CellType
import br.com.cesarsicas.stdandroidteste.constants.CellTypeField

/**
 * Created by julio on 4/19/18.
 */
class Cell {
    constructor(id: Long?, type: CellType?, message: String?, topSpacing: Double?, typefield: CellTypeField?, hidden: Boolean?, required: Boolean?) {
        this.id = id
        this.type = type
        this.message = message
        this.topSpacing = topSpacing
        this.typefield = typefield
        this.hidden = hidden
        this.required = required
    }

    //todo generate adapters to type and typefield

    var id: Long? = null
    var type: CellType? = null
    var message: String? = null
    var topSpacing: Double? = null
    var typefield: CellTypeField? = null
    var hidden: Boolean? = null
    var required: Boolean? = null

    //var show:Int? = null

}