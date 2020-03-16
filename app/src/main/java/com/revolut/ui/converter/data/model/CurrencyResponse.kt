package com.revolut.ui.converter.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.json.JSONObject

@Entity(tableName = "currency")
data class CurrencyResponse(
    @PrimaryKey
    val baseCurrency: String,
    @Embedded
    val rates: JSONObject?
)