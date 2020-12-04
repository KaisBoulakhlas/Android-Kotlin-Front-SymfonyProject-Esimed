package fr.esimed.lebonangle.activity

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import fr.esimed.lebonangle.R
import fr.esimed.lebonangle.api.model.AdvertPostJsonItem
import fr.esimed.lebonangle.api.ApiConnect
import fr.esimed.lebonangle.api.model.CategoryJsonItem
import fr.esimed.lebonangle.getFileName
import kotlinx.android.synthetic.main.activity_create_advert.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.awaitResponse
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class AdvertCreateActivity : AppCompatActivity()  {

    private val PICK_IMAGE = 100
    private val pictures = ArrayList<String>()
    private var imgUri: Uri? = null

    val api = ApiConnect.connect()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_advert)
        setSpinnerCategories()

        btn_upload_image.setOnClickListener {
            openGallery()
        }

        btn_create_advert.setOnClickListener(View.OnClickListener {
            if (advert_value_author.text.isNullOrEmpty() || advert_value_email.text.isNullOrEmpty() || advert_value_content.text.isNullOrEmpty() ||
                    advert_value_price.text.isNullOrEmpty() || advert_value_title.text.isNullOrEmpty()) {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_LONG).show()
                return@OnClickListener
            }

            val newAdvert = AdvertPostJsonItem(
                    advert_value_author.text.toString(),
                    "/api/categories/" + (spinner_category_advert_create.selectedItem as CategoryJsonItem).id,
                    advert_value_content.text.toString(),
                    advert_value_email.text.toString(),
                    advert_value_price.text.toString().toFloat(),
                    advert_value_title.text.toString(),
                    pictures
            )

            GlobalScope.launch(Dispatchers.IO) {
                val response = api.postAdvert(newAdvert).awaitResponse()

                if (response.isSuccessful){
                    withContext(Dispatchers.Main){
                        Toast.makeText(this@AdvertCreateActivity , "Annonce publiée avec succès !", Toast.LENGTH_LONG).show()
                    }
                }
            }
            val intent = Intent(this, CategoryActivity::class.java)
            startActivity(intent)
        })
    }


    private fun setSpinnerCategories(){
        GlobalScope.launch(Dispatchers.IO) {
            val response = api.getCategories().awaitResponse()
            if (response.isSuccessful){
                val categories = response.body()!!
                withContext(Dispatchers.Main) {
                    val adapter = ArrayAdapter<CategoryJsonItem>(this@AdvertCreateActivity, android.R.layout.simple_spinner_item, categories)
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_category_advert_create.adapter = adapter;
                }
            }
        }
    }

    private fun openGallery() {
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(gallery, PICK_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && requestCode == PICK_IMAGE) {
            imgUri = data!!.data
            uploadImage()
        }
    }

    private fun uploadImage(){

        val parcelFileDescriptor = contentResolver.openFileDescriptor(imgUri!!, "r", null) ?: return
        val inputStream = FileInputStream(parcelFileDescriptor.fileDescriptor)
        val file = File(cacheDir, contentResolver.getFileName(imgUri!!))
        val outputStream = FileOutputStream(file)
        inputStream.copyTo(outputStream)
        val requestFile = RequestBody.create(
                "image/*".toMediaTypeOrNull(),
                file
        )

        GlobalScope.launch(Dispatchers.IO) {
            val response = api.postPicture(MultipartBody.Part.createFormData("file", file.name, requestFile)).awaitResponse()
            if (response.isSuccessful){

                val data = response.body()!!
                pictures.add(data.iri)
                println(pictures)
            }
        }
    }

}