package fr.esimed.lebonangle.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import fr.esimed.lebonangle.adapter.AdvertAdapter
import fr.esimed.lebonangle.R
import fr.esimed.lebonangle.api.ApiConnect
import kotlinx.android.synthetic.main.activity_advert.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse


class AdvertActivity : AppCompatActivity() {
    private val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_advert)
        var category = intent.getSerializableExtra("category") as Int
        getAdvertsDataOfCategory(category)
    }


    private fun getAdvertsDataOfCategory(category_id : Int){
        val api = ApiConnect.connect()

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = api.getAdverts(category_id).awaitResponse()
                if (response.isSuccessful) {
                    if(response.body()?.isEmpty()!!){
                        withContext(Dispatchers.Main) {
                            text_notadverts.visibility = View.VISIBLE
                            progress_bar_adverts.visibility = View.GONE
                        }
                    } else{
                        val data = response.body()!!

                        withContext(Dispatchers.Main){

                            recycler_view_adverts.layoutManager = LinearLayoutManager(context)
                            recycler_view_adverts.adapter = AdvertAdapter(context, data)
                            progress_bar_adverts.visibility = View.GONE
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}