package fr.esimed.lebonangle.adapter

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import fr.esimed.lebonangle.R

class CategoryViewHolder(row : View) :  RecyclerView.ViewHolder(row) {
    val cardView : CardView = row.findViewById(R.id.card_view_categories)
    val textViewCatId : TextView = row.findViewById(R.id.text_category_id)
    val textViewCatName : TextView = row.findViewById(R.id.text_category_name)
}