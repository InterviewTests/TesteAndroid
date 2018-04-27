package br.com.cesarsicas.stdandroidteste.constants
import br.com.cesarsicas.stdandroidteste.adapters.TypeAdapter
import com.google.gson.annotations.JsonAdapter

/**
 * Created by julio on 4/19/18.
 */
@JsonAdapter(TypeAdapter::class)
enum class CellType(var value:Int) {
    field(1),
    text(2),
    image(3),
    checkbox(4),
    send(5);

    companion object {
        fun from(findValue: Int): CellType = CellType.values().first { it.value == findValue }
    }
}