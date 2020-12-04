package fr.esimed.lebonangle.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import fr.esimed.lebonangle.R
import fr.esimed.lebonangle.activity.AdvertActivity
import fr.esimed.lebonangle.api.model.CategoryJson

class CategoryAdapter(context: Context, categoriesJSON: CategoryJson) : RecyclerView.Adapter<CategoryViewHolder>() {

    private val context_category:Context = context
    private val categories_json: CategoryJson = categoriesJSON

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(LayoutInflater.from(context_category).inflate(R.layout.category, parent, false))
    }

    override fun getItemCount(): Int {
        return categories_json.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.textViewCatId.text = categories_json[position].id.toString()
        holder.textViewCatName.text = categories_json[position].name

        holder.cardView.setOnClickListener(View.OnClickListener {
            println("DEBUG CLICKED")
            val intentAdvert = Intent(context_category, AdvertActivity::class.java)
            intentAdvert.putExtra("category", categories_json[position].id)
            startActivity(context_category,intentAdvert,null)
        })

    }


}