package ru.lzanelzaz.cft.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import ru.lzanelzaz.cft.model.Bank
import ru.lzanelzaz.cft.model.BinInfo
import ru.lzanelzaz.cft.model.CardNumber
import ru.lzanelzaz.cft.model.Country

private fun <T> T.toJson(): String = Gson().toJson(this)

inline fun <reified T> String.fromJson(): T = Gson().fromJson(this, T::class.java)


class Converters {
    @TypeConverter
    fun toJson(it: BinInfo) = it.toJson()

    @TypeConverter
    fun fromJson(src: String): BinInfo = src.fromJson()

    @TypeConverter
    fun cardNumberToJson(it: CardNumber) = it.toJson()

    @TypeConverter
    fun cardNumberFromJson(src: String): CardNumber = src.fromJson()

    @TypeConverter
    fun CountryToJson(it: Country) = it.toJson()

    @TypeConverter
    fun CountryFromJson(src: String): Country = src.fromJson()

    @TypeConverter
    fun BankToJson(it: Bank) = it.toJson()

    @TypeConverter
    fun BankFromJson(src: String): Bank = src.fromJson()
}
