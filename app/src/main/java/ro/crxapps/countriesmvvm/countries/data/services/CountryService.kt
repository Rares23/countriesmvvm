package ro.crxapps.countriesmvvm.countries.data.services

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import ro.crxapps.countriesmvvm.countries.data.api.CountryApi
import ro.crxapps.countriesmvvm.countries.data.models.Country

class CountryService {
    private val baseUrl = "https://raw.githubusercontent.com"
    private val api: CountryApi

    init {
        api = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(CountryApi::class.java)
    }

    fun getCountries() : Single<List<Country>> {
        return api.getCountries()
    }
}