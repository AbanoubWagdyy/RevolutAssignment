package com.revolut.ui.converter.data.localDataSource

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.revolut.ui.converter.data.model.CurrencyResponse

@Dao
interface ConverterDao {

    @Insert
    fun insertCurrencyResponse(currencyResponse: CurrencyResponse)

    @Query("SELECT * FROM currency WHERE baseCurrency = :base")
    fun getCurrencyResponse(base: String): LiveData<List<CurrencyResponse>>

    @Query("SELECT * FROM currency")
    fun getAllLocalCurrencyResponse(): LiveData<List<CurrencyResponse>>

    @Insert
    fun insertAll(list: List<CurrencyResponse>)
}