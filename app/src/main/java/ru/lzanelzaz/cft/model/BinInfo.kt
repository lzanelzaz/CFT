package ru.lzanelzaz.cft.model

data class BinInfo(
    var bin: String,
    val number: CardNumber = CardNumber(),
    val scheme: String? = null,
    val type: String? = null,
    val brand: String? = null,
    val prepaid: Boolean? = null,
    val country: Country = Country(),
    val bank: Bank = Bank()
)

data class CardNumber(
    val length: String? = null,
    val luhn: Boolean? = null
)

data class Country(
    val alpha2: String? = null,
    val name: String? = null,
    val emoji: String? = null,
    val currency: String? = null,
    val latitude: String? = null,
    val longitude: String? = null
)

data class Bank(
    val name: String? = null,
    val url: String? = null,
    val phone: String? = null,
    val city: String? = null
)
