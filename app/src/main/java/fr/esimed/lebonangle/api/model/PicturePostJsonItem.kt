package fr.esimed.lebonangle.api.model

import com.google.gson.annotations.SerializedName

data class PicturePostJsonItem(
        @SerializedName("@context") val context : String,
        @SerializedName("@id") val iri : String,
        @SerializedName("@type") val type : String,
        @SerializedName("contentUrl") val contentUrl : String,
        @SerializedName("advert") val advert : String
)