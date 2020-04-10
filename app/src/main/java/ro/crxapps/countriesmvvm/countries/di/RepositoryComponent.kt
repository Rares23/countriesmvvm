package ro.crxapps.countriesmvvm.countries.di

import dagger.Component
import ro.crxapps.countriesmvvm.countries.viewmodels.CountryListViewModel

@Component(modules = [RepositoryModule::class])
interface RepositoryComponent {
    fun inject(countryListViewModel: CountryListViewModel)
}