package com.revolut.di

import com.revolut.ui.converter.ui.ConverterFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeThemeFragment(): ConverterFragment
}
