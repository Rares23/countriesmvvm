package ro.crxapps.countriesmvvm.countries.di

import dagger.Component
import ro.crxapps.countriesmvvm.countries.data.repository.CountryRepository

@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject(countryRepository: CountryRepository)
}