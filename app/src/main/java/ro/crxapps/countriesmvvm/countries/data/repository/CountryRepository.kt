package ro.crxapps.countriesmvvm.countries.data.repository

import io.reactivex.Single
import ro.crxapps.countriesmvvm.countries.data.models.Country
import ro.crxapps.countriesmvvm.countries.data.services.CountryService

class CountryRepository {
    private val countryService = CountryService()

    fun getCountries() : Single<List<Country>> = countryService.getCountries()
}