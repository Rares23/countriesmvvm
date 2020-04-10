package ro.crxapps.countriesmvvm.countries.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ro.crxapps.countriesmvvm.countries.data.models.Country

class CountryListViewModel : ViewModel() {
    val countries: MutableLiveData<List<Country>> = MutableLiveData()
    val countryLoadError: MutableLiveData<Boolean> = MutableLiveData()
    val loading: MutableLiveData<Boolean> = MutableLiveData()

    fun refresh() {
        fetchCountries()
    }

    private fun fetchCountries() {
        val mockData: List<Country> = listOf(
            Country("CountryA"),
            Country("CountryB"),
            Country("CountryC"),
            Country("CountryD"),
            Country("CountryE"),
            Country("CountryF"),
            Country("CountryG"),
            Country("CountryH"),
            Country("CountryI"),
            Country("CountryJ"),
            Country("CountryK"),
            Country("CountryL"),
            Country("CountryM"),
            Country("CountryN")
        )

        countryLoadError.value = false
        loading.value = false
        countries.value = mockData
    }
}