package com.mantequilla.walletwizardapp.di

import android.content.Context
import com.mantequilla.walletwizardapp.sharedpreference.PreferenceHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object PreferenceHelperModule {

    @Provides
    fun providePreferenceHelper(@ApplicationContext context: Context) : PreferenceHelper{
        return PreferenceHelper(context)
    }
}