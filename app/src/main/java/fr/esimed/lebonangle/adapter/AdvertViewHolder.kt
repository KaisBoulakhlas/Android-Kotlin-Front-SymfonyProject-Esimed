package fr.esimed.lebonangle.adapter

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.esimed.lebonangle.R

class AdvertViewHolder (row : View) :  RecyclerView.ViewHolder(row) {
    val button_details_advert : Button = row.findViewById(R.id.button_details_advert)
    val textViewAdvertId : TextView = row.findViewById(R.id.text_advert_id)
    val textViewAdvertTitle : TextView = row.findViewById(R.id.text_advert_title)
    val textViewAdvertAuthor : TextView = row.findViewById(R.id.text_advert_author)
    val textViewAdvertMail : TextView = row.findViewById(R.id.text_advert_mail)

}