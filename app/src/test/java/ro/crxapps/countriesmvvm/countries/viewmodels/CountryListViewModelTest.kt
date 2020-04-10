package ro.crxapps.countriesmvvm.countries.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import ro.crxapps.countriesmvvm.countries.data.models.Country
import ro.crxapps.countriesmvvm.countries.data.repository.CountryRepository
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

class CountryListViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    lateinit var countryRepository: CountryRepository

    @InjectMocks
    var countryListViewModel = CountryListViewModel()

    private var testSingle: Single<List<Country>>? = null

    @Test
    fun `test get fetch countries success`() {
        val country = Country("countryName", "capital", "url")
        val countriesList = arrayListOf(country)

        testSingle = Single.just(countriesList)

        Mockito.`when`(countryRepository.getCountries())
            .thenReturn(testSingle)

        countryListViewModel.refresh()
        Assert.assertEquals(1, countryListViewModel.countries.value?.size)
        Assert.assertEquals(false, countryListViewModel.loading.value)
        Assert.assertEquals(false, countryListViewModel.countryLoadError.value)
    }

    @Test
    fun `test get fetch countries failure`() {
        testSingle = Single.error(Throwable())

        Mockito.`when`(countryRepository.getCountries())
            .thenReturn(testSingle)

        countryListViewModel.refresh()
        Assert.assertEquals(true, countryListViewModel.countryLoadError.value)
        Assert.assertEquals(false, countryListViewModel.loading.value)
    }

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Before
    fun setupRxSchedulers() {
        val immediate = object : Scheduler() {
            override fun scheduleDirect(run: Runnable, delay: Long, unit: TimeUnit): Disposable {
                return super.scheduleDirect(run, 0, unit)
            }

            override fun createWorker(): Worker {
                return ExecutorScheduler.ExecutorWorker(Executor { it.run() }, false)
            }
        }

        RxJavaPlugins.setInitIoSchedulerHandler { immediate }
        RxJavaPlugins.setInitComputationSchedulerHandler { immediate }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { immediate }
        RxJavaPlugins.setInitSingleSchedulerHandler { immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { immediate }
    }
}