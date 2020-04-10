package ro.crxapps.countriesmvvm.countries.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_countries.*
import ro.crxapps.countriesmvvm.R
import ro.crxapps.countriesmvvm.countries.adapters.CountryListAdapter
import ro.crxapps.countriesmvvm.countries.viewmodels.CountryListViewModel

class CountriesActivity : AppCompatActivity() {

    lateinit var viewModel: CountryListViewModel
    private var countryListAdapter = CountryListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
            .create(CountryListViewModel::class.java)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_countries)
        initCountryListRecyclerView()

        observeViewModel()

        viewModel.refresh()
    }

    private fun initCountryListRecyclerView() {
        recyclerView_countries.apply {
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = countryListAdapter
        }
    }

    private fun observeViewModel() {
        viewModel.countries.observe(this, Observer { countries ->
            countries?.let {
                recyclerView_countries.visibility = View.VISIBLE
                countryListAdapter.updateCountries(it)
            }
        })

        viewModel.loading.observe(this, Observer {isLoading ->
            isLoading?.let {
                progressBar_loader.visibility = if(it) View.VISIBLE else View.GONE
                if(it) {
                    recyclerView_countries.visibility = View.GONE
                }
            }
        })

        viewModel.countryLoadError.observe(this, Observer {isError ->
            isError?.let {
                textView_error.visibility = if(it) View.VISIBLE else View.GONE
                if(it) {
                    recyclerView_countries.visibility = View.GONE
                }
            }
        })
    }
}