package fr.esimed.lebonangle.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import fr.esimed.lebonangle.adapter.CategoryAdapter
import fr.esimed.lebonangle.R
import fr.esimed.lebonangle.api.ApiConnect
import kotlinx.android.synthetic.main.activity_category.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse


class CategoryActivity : AppCompatActivity() {
    private val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        val intent = Intent(this, AdvertCreateActivity::class.java)
        button_activity_advert_create.setOnClickListener( View.OnClickListener {
            startActivity(intent)
        })

        getCategories()

    }

    private fun getCategories(){

        val api = ApiConnect.connect()

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = api.getCategories().awaitResponse()
                if (response.isSuccessful) {
                    val data = response.body()!!

                    withContext(Dispatchers.Main){

                        recycler_view_categories.layoutManager = LinearLayoutManager(context)
                        recycler_view_categories.adapter = CategoryAdapter(context, data)
                        progress_bar_categories.visibility = View.GONE
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}


