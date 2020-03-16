package com.revolut.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.revolut.api.ConverterService
import com.revolut.data.AppDatabase
import com.revolut.ui.converter.data.ConverterRepository
import com.revolut.ui.converter.data.localDataSource.ConverterDao
import com.revolut.ui.converter.data.remoteDataSource.ConverterRemoteDataSource
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class LegoSetRepositoryTest {
    private lateinit var repository: ConverterRepository
    private val dao = mock(ConverterDao::class.java)
    private val service = mock(ConverterService::class.java)
    private val remoteDataSource = ConverterRemoteDataSource(service)

    private val base = "EUR"

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        val db = mock(AppDatabase::class.java)
        `when`(db.converterDao()).thenReturn(dao)
        `when`(db.runInTransaction(ArgumentMatchers.any())).thenCallRealMethod()
        repository = ConverterRepository(dao, remoteDataSource)
    }

    @Test
    fun loadCurrencyRatesFromNetwork() {
        runBlocking {
            verify(dao, never()).getCurrencyResponse(base)
            verifyZeroInteractions(dao)
        }
    }
}