package com.revolut.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.revolut.ui.converter.data.ConverterRepository
import com.revolut.ui.converter.ui.ConverterViewModel
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class LegoSetViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val repository = mock(ConverterRepository::class.java)
    private var viewModel = ConverterViewModel(repository)

    @Test
    fun testNull() {
        verify(viewModel, never()).getRates(null)
    }

    @Test
    fun testWithExistingCurrency() {
        verify(viewModel, never()).getRates("EUR")
    }

    @Test
    fun testWithNonExistingCurrency() {
        verify(viewModel, never()).getRates("Test")
    }
}