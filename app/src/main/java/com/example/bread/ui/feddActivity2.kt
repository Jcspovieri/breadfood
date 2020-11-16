package com.example.bread.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bread.R
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference


private var mStorageRef: StorageReference? = null

class feddActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fedd2)
        mStorageRef = FirebaseStorage.getInstance().getReference();



        }
    }
