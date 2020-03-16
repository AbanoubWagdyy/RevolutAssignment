package com.revolut.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers.`is`
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class ConverterServiceTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var service: ConverterService

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ConverterService::class.java)
    }

    @Test
    fun requestCurrencyRatesWithExistingCurrency() {
        runBlocking {
            val resultResponse = service.getCurrencyRates("EUR").body()

            val request = mockWebServer.takeRequest()

            assertNotNull(resultResponse)

            assertThat(request.path, `is`("android/latest"))

            assertNotNull(resultResponse?.rates)
        }
    }

    @Test
    fun requestCurrencyRatesWithNonExistingCurrency() {
        runBlocking {
            val resultResponse = service.getCurrencyRates("Test").body()

            val request = mockWebServer.takeRequest()

            assertNotNull(resultResponse)

            assertThat(request.path, `is`("android/latest"))
            assertThat(resultResponse?.rates, null)
        }
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }
}