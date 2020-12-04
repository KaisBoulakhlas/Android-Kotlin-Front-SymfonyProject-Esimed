package fr.esimed.lebonangle.adapter

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import fr.esimed.lebonangle.R

class PictureViewHolder (row : View) :  RecyclerView.ViewHolder(row) {
    val imageview: ImageView = row.findViewById(R.id.img_picture)

}