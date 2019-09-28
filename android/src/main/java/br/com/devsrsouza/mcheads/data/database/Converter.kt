package br.com.devsrsouza.mcheads.data.database

import androidx.room.TypeConverter
import br.com.devsrsouza.mcheads.common.HeadCategory

class Converter {
    private fun <T : Enum<T>> T.toInt(): Int = this.ordinal
    private inline fun <reified T : Enum<T>> Int.toEnum(): T = enumValues<T>()[this]

    @TypeConverter fun categoryToInt(value: HeadCategory) = value.toInt()
    @TypeConverter fun intToHeadCategory(value: Int) = value.toEnum<HeadCategory>()
}