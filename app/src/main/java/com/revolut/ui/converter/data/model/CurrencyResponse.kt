package com.revolut.ui.converter.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency")
data class CurrencyResponse(
    @PrimaryKey
    val baseCurrency: String,
    @Embedded
    val rates: Rates?
)