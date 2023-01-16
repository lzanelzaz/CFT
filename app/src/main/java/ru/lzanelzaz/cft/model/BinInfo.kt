package ru.lzanelzaz.cft.model

import androidx.room.Embedded
import com.squareup.moshi.Json

data class BinInfo(
    @Embedded
    val number: CardNumber = CardNumber(),
    val scheme: String = "",
    val type: String = "",
    val brand: String = "",
    val prepaid: Boolean? = null,
    @Embedded
    val country: Country = Country(),
    @Embedded
    val bank: Bank = Bank()
)

data class CardNumber(
    val length: String? = null,
    val luhn: Boolean? = null
)

data class Country(
    val alpha2: String = "",
    @Json(name = "name")
    val countryName: String = "",
    val emoji: String = "",
    val currency: String = "",
    val latitude: String = "",
    val longitude: String = ""
)

data class Bank(
    @Json(name = "name")
    val bankName: String = "",
    val url: String? = null,
    val phone: String? = null,
    val city: String? = null
)
