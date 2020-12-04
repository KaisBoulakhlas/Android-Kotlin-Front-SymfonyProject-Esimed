package fr.esimed.lebonangle.api.model

import java.io.Serializable

data class AdvertJsonItem(
    val author: String,
    val category: String,
    val content: String,
    val createdAt: String,
    val email: String,
    val id: Int,
    val pictures: List<String>,
    val price: Int,
    val publishedAt: String,
    val state: String,
    val title: String
):Serializable