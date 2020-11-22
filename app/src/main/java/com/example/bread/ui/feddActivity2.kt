package com.example.bread.ui


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bread.Cadastro_cliente
import com.example.bread.PostActivity
import com.example.bread.R
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_fedd2.*
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File



private var mStorageRef: StorageReference? = null

class feddActivity2 : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fedd2)
        mStorageRef = FirebaseStorage.getInstance().getReference();



        postbutton.setOnClickListener {
            val Intent: Intent = Intent(this, PostActivity::class.java)
            startActivity(Intent)


        }

    }
}

