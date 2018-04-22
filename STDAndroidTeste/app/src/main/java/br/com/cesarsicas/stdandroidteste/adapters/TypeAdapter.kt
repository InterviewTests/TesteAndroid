package br.com.cesarsicas.stdandroidteste.adapters
import br.com.cesarsicas.stdandroidteste.constants.CellType
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement

/**
 * Created by julio on 4/21/18.
 */

class TypeAdapter : JsonDeserializer<CellType?> {
    override fun deserialize(json: JsonElement?, typeOfT: java.lang.reflect.Type?, context: JsonDeserializationContext?): CellType? {
        val value = json?.asInt
        try {
            return CellType.from(value ?:0)

        } catch (ex: Exception) {
            when (value) {
                1 -> return CellType.field
                2 -> return CellType.text
                3 -> return CellType.image
                4 -> return CellType.checkbox

            }

            return null
        }

    }
}