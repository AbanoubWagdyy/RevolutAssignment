package com.revolut.api

import com.revolut.ui.converter.data.model.CurrencyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ConverterService {

    @GET("android/latest")
    suspend fun getCurrencyRates(@Query("base") base: String? = ""): Response<CurrencyResponse>
}
