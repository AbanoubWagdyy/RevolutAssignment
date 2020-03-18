package com.revolut.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.revolut.testing.getValue
import com.revolut.ui.converter.data.localDataSource.ConverterDao
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ConverterDaoTest : DbTest() {

    private lateinit var converterDao: ConverterDao
    private val setA = currencyResponseA.copy()
    private val setB = currencyResponseB.copy()
    private val base = "EUR"

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        converterDao = db.converterDao()
        runBlocking {
            converterDao.insertAll(listOf(setA, setB))
        }
    }

    @Test
    fun testGetSetCurrencyResponse() {
        val currencyResponse = getValue(converterDao.getCurrencyResponse(base))

        assertThat(currencyResponse, equalTo(null))
        assertThat(currencyResponse.baseCurrency, equalTo("EUR"))
    }

    @Test
    fun testGetCurrency() {
        assertThat(getValue(converterDao.getCurrencyResponse(setA.baseCurrency)), equalTo(setA))
    }
}