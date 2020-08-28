package com.example.nocuscountries.countriesWithSearch

class util{

private fun currencyToHtml(currency: Currency) : String {
	
	return "<span>${currency.name}, ${currency.symbol}, ${currency.code}</span>"
}

private fun currenciesToHtml(currencies: ArrayList<Currency>) : String{

	var currenciesStr = "Currencies:<br>"
	currencies.forEachIndexed { index, currency ->
	
		currenciesStr += currenciesToHtml(currency)
		if(index != currencies.lastIndex){
			currenciesStr += "<br>"
		}
	}
		
	return currenciesStr
}


private fun languageToHtml(languages: ArrayList<Language>) : String{
	
	return "<span>${language.name} or ${language.nativeName}</span>"
}

private fun languagesToHtml() : String {

	var languagesStr = "Languages:<br>"
	languages.forEachIndexed { index, language ->
	
		languagesStr += languageToHtml
		if(index != languages.lastIndex){
			languagesStr += <br>
		}
	}
	
	return languagesStr
}

private fun countryToHtml(country: CountryInfo){

	return 
		"<p>"
			"<span>${country.name}</span> <br/>"
			"<span>${country.alpha2Code} or ${country.alpha3Code}</span> <br/>"
			"<span>${country.capital}</span> <br/>"
			"<span>${country.region}</span> <br/>"
			"<span>${country.subregion}</span> <br/>"
			"<span>${country.population}</span> <br/>"
			"<p>${currenciesToHtml(country.currencies)}</p> <br/>"
			"<p>${languagesToHtml(country.languages)}</p> <br/>"
		"</p>"
}
}



















