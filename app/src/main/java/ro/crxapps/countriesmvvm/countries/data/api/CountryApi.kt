package ro.crxapps.countriesmvvm.countries.data.api

import io.reactivex.Single
import retrofit2.http.GET
import ro.crxapps.countriesmvvm.countries.data.models.Country

interface CountryApi {
    @GET("DevTides/countries/master/countriesV2.json")
    fun getCountries() : Single<List<Country>>

}