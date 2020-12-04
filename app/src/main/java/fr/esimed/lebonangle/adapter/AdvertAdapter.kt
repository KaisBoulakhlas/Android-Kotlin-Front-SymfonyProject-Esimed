package fr.esimed.lebonangle.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import fr.esimed.lebonangle.R
import fr.esimed.lebonangle.activity.AdvertDetailsActivity
import fr.esimed.lebonangle.api.model.AdvertJson

class AdvertAdapter(context: Context, advertsJSON: AdvertJson) : RecyclerView.Adapter<AdvertViewHolder>() {

    private val context_advert:Context = context
    private val adverts_json: AdvertJson = advertsJSON

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdvertViewHolder {
        return AdvertViewHolder(LayoutInflater.from(context_advert).inflate(R.layout.advert, parent, false))
    }

    override fun getItemCount(): Int {
        return adverts_json.size
    }

    override fun onBindViewHolder(holder: AdvertViewHolder, position: Int) {
        holder.textViewAdvertId.text = adverts_json[position].id.toString()
        holder.textViewAdvertTitle.text = adverts_json[position].title
        holder.textViewAdvertAuthor.text = adverts_json[position].author
        holder.textViewAdvertMail.text = adverts_json[position].email

        holder.button_details_advert.setOnClickListener(View.OnClickListener {
            println("DEBUG CLICKED")
            val intentAdvert = Intent(context_advert, AdvertDetailsActivity::class.java)
            intentAdvert.putExtra("advert", adverts_json[position])
            startActivity(context_advert, intentAdvert, null)
        })
    }
}