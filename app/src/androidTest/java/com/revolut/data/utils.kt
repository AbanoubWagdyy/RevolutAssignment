package com.revolut.data

import com.revolut.ui.converter.data.model.CurrencyResponse
import com.revolut.ui.converter.data.model.Rates

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

val currencyResponseA = CurrencyResponse(base, rates)

val currencyResponseB = CurrencyResponse(base2, rates2)