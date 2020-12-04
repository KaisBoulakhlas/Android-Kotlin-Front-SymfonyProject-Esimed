package fr.esimed.lebonangle.api


import fr.esimed.lebonangle.api.model.*
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface ApiRequests {
    @GET("adverts.json")
    fun getAdverts(@Query("category.id") categoryId: Int): Call<AdvertJson>

    @GET("pictures.json")
    fun getPictures(@Query("advert.id") advert: Int): Call<PictureJson>

    @GET("categories.json")
    fun getCategories(): Call<CategoryJson>

    @POST("adverts")
    fun postAdvert(@Body advert: AdvertPostJsonItem): Call<AdvertJsonItem>

    @Headers(
            "Accept: application/ld+json"
    )
    @Multipart
    @POST("pictures")
    fun postPicture(
            @Part file: MultipartBody.Part
    ): Call<PicturePostJsonItem>
}
