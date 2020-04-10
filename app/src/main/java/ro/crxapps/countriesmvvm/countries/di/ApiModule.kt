package ro.crxapps.countriesmvvm.countries.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import ro.crxapps.countriesmvvm.countries.data.api.CountryApi

@Module
class ApiModule {
    private val baseUrl = "https://raw.githubusercontent.com"

    @Provides
    fun provideCountryApi() : CountryApi {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(CountryApi::class.java)
    }
}