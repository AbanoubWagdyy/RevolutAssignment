package com.revolut.ui.converter.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.revolut.data.Result
import com.revolut.ui.converter.data.ConverterRepository
import com.revolut.ui.converter.data.model.CurrencyResponse
import javax.inject.Inject

class ConverterViewModel @Inject constructor(val mRepository: ConverterRepository) : ViewModel() {

    fun getRates(base: String?): LiveData<Result<CurrencyResponse>> {
        return mRepository.getCurrencyRates(base)
    }
}