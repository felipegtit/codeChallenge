package com.felipegsolutions.codechallenge.di

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.felipegsolutions.codechallenge.model.Pics
import com.felipegsolutions.codechallenge.presentation.App
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): App {
        return app as App
    }

    @Singleton
    @Provides
    fun provideLiveDataPics(): MutableLiveData<Pics> {
        return MutableLiveData()
    }

    @Singleton
    @Provides
    fun provideRetroInstance(): Retrofit {
        val BASE_URL = "https://pixabay.com"
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}