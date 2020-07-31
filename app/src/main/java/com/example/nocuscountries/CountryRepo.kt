import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nocuscountries.CountryApiService
import com.example.nocuscountries.CountryCache
import com.example.nocuscountries.CountryInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CountryRepo(
    private val countryApiService : CountryApiService,
    private val countryCache : CountryCache
) {

    fun getCountries() : LiveData<ArrayList<CountryInfo>> {

        // check in-memory - cache
        val cached : LiveData<ArrayList<CountryInfo>>? = countryCache.getCountries()
        if (cached != null) return cached

        // pull from api
        val data = MutableLiveData<ArrayList<CountryInfo>>()

        // add to cache
        countryCache.put(data)

        countryApiService.getCountriesInfo().enqueue(object : Callback<ArrayList<CountryInfo>> {

            override fun onFailure(call: Call<ArrayList<CountryInfo>>, t: Throwable) {
                Log.e("NocusCountries", "msg: ${t.localizedMessage}; \n localised msg: ${t.message}\n")
            }

            override fun onResponse(
                call: Call<ArrayList<CountryInfo>>,
                response: Response<ArrayList<CountryInfo>>
            ) {
                data.value = response.body()
                Log.i("NocusCountries", "country response from http api call\n")
            }
        })

        return data
    }
}





















//
//val countryApiService by lazy {
//    CountryApiService.create()
//}
//
//var disposable: Disposable? = null

//
//fun getCountriesDataFromApi(){
//    disposable =
//        countryApiService.getCountriesInfo()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                { result -> countries},
//                { error -> err = error.message}
//            )
//}
