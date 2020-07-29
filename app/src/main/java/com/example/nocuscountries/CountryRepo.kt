import com.example.nocuscountries.CountryCache
import com.example.nocuscountries.CountryInfo
import com.example.nocuscountries.CountryInfoDao
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nocuscountries.CountryApiService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CountryRepo(
    private val countryApiService : CountryApiService,
    private val countryCache : CountryCache,
    private val countryInfoDao: CountryInfoDao
) {

    suspend fun getCountries() : LiveData<ArrayList<CountryInfo>> {

        // check inmemory - cache
        val cached : LiveData<ArrayList<CountryInfo>>? = countryCache.getCountries()
        if (cached != null) return cached

        // check disk - db
        val db = countryInfoDao.getCountries()
        if (db != null) return db

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
                val res = response.body()
                // data.value = response.body()
                Log.i("NocusCountries", "country response from http api call\n")

                // save response to db
                GlobalScope.launch {
                    countryInfoDao.delete()
                    res?.let { countryInfoDao.insert(it) }
                    data.value = countryInfoDao.getCountries().value
                }
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
