package com.revolut.data

import com.revolut.ui.converter.data.model.CurrencyResponse
import com.revolut.ui.converter.data.model.Rates
import org.json.JSONObject

val base = "EUR"
val base2 = "AUD"

val rates = Rates(
    10.0,
    10.0,
    10.0,
    10.0,
    10.0,
    10.0,
    10.0,
    10.0,
    10.0,
    10.0,
    10.0,
    10.0,
    10.0,
    10.0,
    10.0,
    10.0,
    10.0,
    10.0,
    10.0,
    10.0,
    10.0,
    10.0,
    10.0,
    10.0,
    10.0,
    10.0,
    10.0,
    10.0,
    10.0,
    10.0,
    10.0
)

val jsonObject =
    JSONObject("{\"AUD\":1.601,\"BGN\":1.971,\"BRL\":4.263,\"CAD\":1.515,\"CHF\":1.149,\"CNY\":7.736,\"CZK\":25.93,\"DKK\":7.567,\"GBP\":0.876,\"HKD\":8.947,\"HRK\":7.442,\"HUF\":323.77,\"IDR\":16085.473,\"ILS\":4.113,\"INR\":81.751,\"ISK\":135.205,\"JPY\":125.814,\"KRW\":1285.437,\"MXN\":22.064,\"MYR\":4.694,\"NOK\":9.814,\"NZD\":1.667,\"PHP\":59.98,\"PLN\":4.388,\"RON\":4.811,\"RUB\":74.896,\"SEK\":10.659,\"SGD\":1.553,\"THB\":35.37,\"USD\":1.144,\"ZAR\":15.975}")

val rates2 = Rates(
    12.0,
    12.0,
    12.0,
    12.0,
    12.0,
    12.0,
    12.0,
    12.0,
    12.0,
    12.0,
    12.0,
    12.0,
    12.0,
    12.0,
    12.0,
    12.0,
    12.0,
    12.0,
    12.0,
    12.0,
    12.0,
    12.0,
    12.0,
    12.0,
    12.0,
    12.0,
    12.0,
    12.0,
    12.0,
    12.0,
    12.0
)

val currencyResponseA = CurrencyResponse(base, jsonObject)

val currencyResponseB = CurrencyResponse(base2, null)