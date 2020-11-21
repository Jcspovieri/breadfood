package com.example.bread

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import kotlinx.android.synthetic.main.activity_post.*
import java.io.File
import java.util.*

class PostActivity : AppCompatActivity() {

    companion object {
        private val IMAGE_CODE = 1
        private val PERMISSION_CODE = 2
    }

    lateinit var mStorageRef: StorageReference
    lateinit var imageUri: Uri
    lateinit var imageUriDownload: Uri
    lateinit var imageViewProduct: ImageView
    var imageLink = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        var spinnerCategoria = findViewById<Spinner>(R.id.spinnerCategoria)
        imageViewProduct = findViewById<ImageView>(R.id.imageViewProduct)
        var buttonUpload = findViewById<Button>(R.id.buttonUpload)

        mStorageRef = FirebaseStorage.getInstance().reference

        spinnerCategoria.adapter = ArrayAdapter(this,
            R.layout.support_simple_spinner_dropdown_item,
            resources.getStringArray(R.array.Categoria))

        imageViewProduct.setOnClickListener{
            selectImage()
        }

        buttonUpload.setOnClickListener {
            var imageRef = mStorageRef.child("images/${imageUri.lastPathSegment}")

            var uploadTask = imageRef.putFile(imageUri)

            uploadTask.addOnSuccessListener {
                imageLink = it.storage.downloadUrl.toString()
                val link = imageRef.downloadUrl.toString()
                val downloadUrl = it.toString()
                Toast.makeText(this, "Success: $imageLink", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{
                Toast.makeText(this, "Error: ${it.localizedMessage}", Toast.LENGTH_SHORT).show()
            }

            imageRef.downloadUrl.addOnSuccessListener {
                imageUriDownload = it
            }
        }
    }

    private fun selectImage() {
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
            requestPermissions(permissions, PERMISSION_CODE)
        } else {
            openGalleryAndPickImage()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openGalleryAndPickImage()
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun openGalleryAndPickImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type ="image/*"
        startActivityForResult(intent, IMAGE_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_CODE){
            imageUri = data?.data!!
            imageViewProduct.setImageURI(imageUri)
        }
    }
}