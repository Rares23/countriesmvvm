package ro.crxapps.countriesmvvm.countries.di

import dagger.Module
import dagger.Provides
import ro.crxapps.countriesmvvm.countries.data.repository.CountryRepository

@Module
class RepositoryModule {
    @Provides
    fun provideCountryRepository() : CountryRepository {
        return CountryRepository()
    }
}