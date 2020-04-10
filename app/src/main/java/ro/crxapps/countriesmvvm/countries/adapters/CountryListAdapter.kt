package ro.crxapps.countriesmvvm.countries.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.view_item_country.view.*
import ro.crxapps.countriesmvvm.R
import ro.crxapps.countriesmvvm.countries.data.models.Country

class CountryListAdapter(var countries: ArrayList<Country>) :
    RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CountryViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.view_item_country, parent, false)
    )

    override fun getItemCount() = countries.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countries[position])
    }

    fun updateCountries(newCountries: List<Country>) {
        countries.clear()
        countries.addAll(newCountries)
        notifyDataSetChanged()
    }

    class CountryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val countryTitle: TextView = view.textView_title
        private val countryCapital: TextView = view.textView_capital
        private val countryImage: ImageView = view.imageView_image

        fun bind(country: Country) {
            countryTitle.text = country.countryName
            countryCapital.text = country.countryCapital
        }
    }
}