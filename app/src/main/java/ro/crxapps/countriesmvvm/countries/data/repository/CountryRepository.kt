package ro.crxapps.countriesmvvm.countries.data.repository

import io.reactivex.Single
import ro.crxapps.countriesmvvm.countries.data.api.CountryApi
import ro.crxapps.countriesmvvm.countries.data.models.Country
import ro.crxapps.countriesmvvm.countries.di.DaggerApiComponent
import javax.inject.Inject

class CountryRepository {

    @Inject
    lateinit var api: CountryApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getCountries() : Single<List<Country>> {
        return api.getCountries()
    }
}