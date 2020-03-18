package com.revolut.ui.converter.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.revolut.ui.converter.data.localDataSource.ConverterDao
import com.revolut.ui.converter.data.model.CurrencyResponse
import com.revolut.ui.converter.data.remoteDataSource.ConverterRemoteDataSource
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import com.revolut.data.Result
import kotlinx.coroutines.delay

class ConverterRepository @Inject constructor(
    private val converterDao: ConverterDao,
    private val remoteSource: ConverterRemoteDataSource
) {

    private var baseCurrency: String = "EUR"

    fun getCurrencyRates(): LiveData<Result<CurrencyResponse>> {

        return liveData(Dispatchers.IO) {
            emit(Result.loading(null))
            while (true) {
                val source =
                    converterDao.getCurrencyResponse(baseCurrency).map { Result.success(it) }
                emitSource(source)
                val updatedCurrencyResponse = remoteSource.fetchCurrencyRates(baseCurrency)
                if (updatedCurrencyResponse.status == Result.Status.SUCCESS) {
                    updatedCurrencyResponse.data?.let { currencyResponse ->
                        converterDao.insertCurrencyResponse(
                            currencyResponse
                        )
                    }
                }
                delay(5000)
            }
        }
    }

    fun setCurrency(base: String?) {
        if (base != null) {
            this.baseCurrency = base
        }
    }
}