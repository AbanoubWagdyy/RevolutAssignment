package com.revolut.ui.converter.data.model

data class Rates(
    val AUD: Double,
    val EUR: Double,
    val BGN: Double,
    val BRL: Double,
    val CAD: Double,
    val CHF: Double,
    val CNY: Double,
    val CZK: Double,
    val DKK: Double,
    val GBP: Double,
    val HKD: Double,
    val HRK: Double,
    val HUF: Double,
    val IDR: Double,
    val ILS: Double,
    val INR: Double,
    val ISK: Double,
    val JPY: Double,
    val KRW: Double,
    val MXN: Double,
    val MYR: Double,
    val NOK: Double,
    val NZD: Double,
    val PHP: Double,
    val PLN: Double,
    val RON: Double,
    val RUB: Double,
    val SEK: Double,
    val SGD: Double,
    val THB: Double,
    val USD: Double,
    val ZAR: Double
) {
    fun toRecyclerRateList(): MutableList<RecyclerRate> {
        val list = mutableListOf<RecyclerRate>()

        var recyclerRate = RecyclerRate()
        recyclerRate.baseCurrency = "EUR"
        recyclerRate.baseCurrencyRate = this.EUR
        recyclerRate.isFirstResponder = false

        list.add(recyclerRate)

        recyclerRate = RecyclerRate()
        recyclerRate.baseCurrency = "USD"
        recyclerRate.baseCurrencyRate = this.USD
        recyclerRate.isFirstResponder = false

        list.add(recyclerRate)

        recyclerRate = RecyclerRate()
        recyclerRate.baseCurrency = "SEK"
        recyclerRate.baseCurrencyRate = this.SEK
        recyclerRate.isFirstResponder = false

        list.add(recyclerRate)

        recyclerRate = RecyclerRate()
        recyclerRate.baseCurrency = "CAD"
        recyclerRate.baseCurrencyRate = this.CAD
        recyclerRate.isFirstResponder = false

        list.add(recyclerRate)

        return list
    }
}