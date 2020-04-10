package ro.crxapps.countriesmvvm.countries.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ro.crxapps.countriesmvvm.countries.data.models.Country
import ro.crxapps.countriesmvvm.countries.data.repository.CountryRepository
import ro.crxapps.countriesmvvm.countries.di.DaggerRepositoryComponent
import javax.inject.Inject

class CountryListViewModel : ViewModel() {

    @Inject
    lateinit var countryRepository: CountryRepository

    private val disposable = CompositeDisposable()

    val countries: MutableLiveData<List<Country>> = MutableLiveData()
    val countryLoadError: MutableLiveData<Boolean> = MutableLiveData()
    val loading: MutableLiveData<Boolean> = MutableLiveData()

    init {
        DaggerRepositoryComponent.create().inject(this)
    }

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