package com.revolut.ui.converter.data

import androidx.lifecycle.LiveData
import com.revolut.data.Result
import com.revolut.data.resultLiveData
import com.revolut.ui.converter.data.localDataSource.ConverterDao
import com.revolut.ui.converter.data.model.CurrencyResponse
import com.revolut.ui.converter.data.remoteDataSource.ConverterRemoteDataSource
import javax.inject.Inject

class ConverterRepository @Inject constructor(
    private val converterDao: ConverterDao,
    private val remoteSource: ConverterRemoteDataSource
) {

    var baseCurrency: String = "EUR"

    fun getCurrencyRates(): LiveData<Result<CurrencyResponse>> {
        return resultLiveData(
            databaseQuery = {
                getDatabaseCall()
            },
            networkCall = {
                getNetworkCall()
            },
            saveCallResult = { converterDao.insertCurrencyResponse(it) })
    }

    suspend fun getNetworkCall(): Result<CurrencyResponse> {
        return remoteSource.fetchCurrencyRates(baseCurrency)
    }

    fun getDatabaseCall(): LiveData<CurrencyResponse> {
        return converterDao.getCurrencyResponse(baseCurrency)
    }

    fun setCurrency(base: String?) {
        if (base != null) {
            this.baseCurrency = base
        }
    }
}