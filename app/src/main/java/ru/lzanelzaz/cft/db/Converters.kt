package ru.lzanelzaz.cft.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import ru.lzanelzaz.cft.model.BinInfo
import ru.lzanelzaz.cft.model.CardNumber

private fun <T> T.toJson(): String = Gson().toJson(this)

inline fun <reified T> String.fromJson(): T = Gson().fromJson(this, T::class.java)


class Converters {
    @TypeConverter
    fun toJson(it: BinInfo) = it.toJson()

    @TypeConverter
    fun fromJson(src: String): CardNumber = src.fromJson()
}
