package ru.lzanelzaz.cft.model

data class BinInfo(
    val number: CardNumber = CardNumber(),
    val scheme: String = "",
    val type: String = "",
    val brand: String = "",
    val prepaid: Boolean? = null,
    val country: Country = Country(),
    val bank: Bank = Bank()
)

data class CardNumber(
    val length: String? = null,
    val luhn: Boolean? = null
)

data class Country(
    val alpha2: String = "",
    val name: String = "",
    val emoji: String = "",
    val currency: String = "",
    val latitude: String = "",
    val longitude: String = ""
)

data class Bank(
    val name: String = "",
    val url: String? = null,
    val phone: String? = null,
    val city: String? = null
)
