package fr.esimed.lebonangle.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import fr.esimed.lebonangle.api.model.AdvertJsonItem
import kotlinx.android.synthetic.main.activity_details_advert.*
import android.icu.text.SimpleDateFormat
import androidx.recyclerview.widget.LinearLayoutManager
import fr.esimed.lebonangle.adapter.PictureAdapter
import fr.esimed.lebonangle.R
import fr.esimed.lebonangle.api.ApiConnect
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse
import java.util.*


class AdvertDetailsActivity  : AppCompatActivity() {
    private var context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_advert)
        var currentAdvert = intent.getSerializableExtra("advert") as AdvertJsonItem
        getPicturesOfAdvert(currentAdvert)
    }

    private fun getPicturesOfAdvert(advert : AdvertJsonItem){
        val api = ApiConnect.connect()

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = api.getPictures(advert.id).awaitResponse()
                if (response.isSuccessful) {
                        val data = response.body()!!
                        println("Pictures: $data")
                    withContext(Dispatchers.Main){

                        txt_value_title.text = advert.title
                        txt_value_author.text = advert.author
                        txt_value_content.text = advert.content
                        txt_value_email.text = advert.email
                        txt_value_price.text = advert.price.toString() + "€"
                        val date = SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE).parse(advert.publishedAt)
                        text_published_at.text =  SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE).format(date)


                        recycler_view_pictures.adapter = PictureAdapter(context, data)
                        recycler_view_pictures.layoutManager = LinearLayoutManager(context)

                        progress_bar_adverts_details.visibility = View.GONE
                        linearlayout_details.visibility = View.VISIBLE
                    }
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}