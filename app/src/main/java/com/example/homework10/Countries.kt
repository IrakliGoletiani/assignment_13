package com.example.homework10

data class Countries(
    val name: String? = null,
    val capital: String? = null,
    val region: String? = null,
    val population: Int? = null,
    val borders: List<String>? = null,
    val currencies: List<Currency>? = null
)

data class Currency(
    val code: String? = null,
    val name: String? = null,
    val symbol: String? = null,
)