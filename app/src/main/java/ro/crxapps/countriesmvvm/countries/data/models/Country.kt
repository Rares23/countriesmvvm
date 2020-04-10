package ro.crxapps.countriesmvvm.countries.data.models

import com.squareup.moshi.Json

data class Country(
    @field:Json(name = "name") val countryName: String?,
    @field:Json(name = "capital") val countryCapital: String? = null,
    @field:Json(name = "flagPNG") val countryImageUrl: String? = null
)