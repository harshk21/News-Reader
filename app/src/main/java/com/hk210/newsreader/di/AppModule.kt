package com.hk210.newsreader.di

import android.content.Context
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.hk210.newsreader.BuildConfig
import com.hk210.newsreader.NewsReaderApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun getInterceptor(): StethoInterceptor {
        return StethoInterceptor()
    }

    @Provides
    @Singleton
    fun getGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun getOkHttpClient(stethoInterceptor: StethoInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(stethoInterceptor)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer ${BuildConfig.API_KEY}")
                .build()
                chain.proceed(newRequest)
            }
            .build()
    }

    @Provides
    @Singleton
    fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun getContext(@ApplicationContext context: Context): Context = context.applicationContext
}
