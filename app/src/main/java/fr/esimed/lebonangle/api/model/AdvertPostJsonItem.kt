package fr.esimed.lebonangle.api.model


import java.io.Serializable

data class AdvertPostJsonItem(
        val author: String,
        val category: String,
        val content: String,
        val email: String,
        val price: Float,
        val title: String,
        val pictures: List<String>
): Serializable