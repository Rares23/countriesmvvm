package ro.crxapps.countriesmvvm.countries.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ro.crxapps.countriesmvvm.countries.data.models.Country
import ro.crxapps.countriesmvvm.countries.data.repository.CountryRepository

class CountryListViewModel : ViewModel() {

    private val countryRepository = CountryRepository()

    private val disposable = CompositeDisposable()

    val countries: MutableLiveData<List<Country>> = MutableLiveData()
    val countryLoadError: MutableLiveData<Boolean> = MutableLiveData()
    val loading: MutableLiveData<Boolean> = MutableLiveData()

    fun refresh() {
        fetchCountries()
    }

    private fun fetchCountries() {
        loading.value = true
        countryLoadError.value = false

        disposable.add(
            countryRepository.getCountries()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        countries.value = it
                        loading.value = false
                        countryLoadError.value = false
                    },
                    {
                        it.printStackTrace()
                        loading.value = false
                        countryLoadError.value = true
                    }
                )
        )
    }

    override fun onCleared() {
        super.onCleared()

        disposable.clear()
    }
}