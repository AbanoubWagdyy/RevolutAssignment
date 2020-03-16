package com.revolut.ui.converter.data.remoteDataSource

import com.revolut.api.ConverterService
import com.revolut.api.BaseDataSource
import com.revolut.testing.OpenForTesting
import javax.inject.Inject

@OpenForTesting
class ConverterRemoteDataSource @Inject constructor(private val service: ConverterService) :
    BaseDataSource() {

    suspend fun fetchCurrencyRates(base: String) = getResult { service.getCurrencyRates(base) }
}