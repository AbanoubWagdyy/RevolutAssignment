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

    fun getCurrencyRates(base: String?): LiveData<Result<List<CurrencyResponse>>> {
        return resultLiveData(
            databaseQuery = {
                if (base != null)
                    converterDao.getCurrencyResponse(base)
                else
                    converterDao.getCurrencyResponse("")
            },
            networkCall = {
                if (base != null)
                    remoteSource.fetchCurrencyRates(base)
                else
                    remoteSource.fetchCurrencyRates("")
            },
            saveCallResult = { converterDao.insertCurrencyResponse(it) })
    }
}