package fr.esimed.lebonangle.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.esimed.lebonangle.R
import fr.esimed.lebonangle.api.model.PictureJson

class PictureAdapter(context: Context, picturesJSON: PictureJson) : RecyclerView.Adapter<PictureViewHolder>() {
    private val context_picture: Context = context
    private val pictures_json: PictureJson = picturesJSON

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder {
        return PictureViewHolder(LayoutInflater.from(context_picture).inflate(R.layout.picture, parent, false))
    }

    override fun getItemCount(): Int {
        return pictures_json.size
    }

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
        Picasso.get().load("http://10.0.2.2:8000" + pictures_json[position].contentUrl).into(holder.imageview);
    }
}