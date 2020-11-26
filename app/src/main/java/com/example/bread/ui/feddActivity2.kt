package com.example.bread.ui


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bread.PostActivity
import com.example.bread.R
import com.example.bread.RecyclerPostAdapter
import com.example.bread.model.Post
import com.google.firebase.database.*
import com.google.firebase.firestore.core.Query
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_fedd2.*

class feddActivity2 : AppCompatActivity() {

    private var mStorageRef: StorageReference? = null
    lateinit var database: FirebaseDatabase
    lateinit var databaseReference: DatabaseReference
    var postList = mutableListOf<Post?>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fedd2)

        //banco
        mStorageRef = FirebaseStorage.getInstance().getReference();
        database = FirebaseDatabase.getInstance()
        databaseReference = database.reference

        val linearLayoutManager = LinearLayoutManager(this)
        recyclerViewFeed.layoutManager = linearLayoutManager
        recyclerViewFeed.setHasFixedSize(true)

        val listener = object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach {
                    val post = it.getValue(Post::class.java)
                    postList.add(post)
                }
                var adapter = RecyclerPostAdapter(applicationContext, postList)
                recyclerViewFeed.adapter = adapter
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }

        val query = databaseReference.child("posts").addValueEventListener(listener)

        postbutton.setOnClickListener {
            val intent: Intent = Intent(this, PostActivity::class.java)
            startActivity(intent)
        }
    }


    fun clearAll(){
        if(postList != null){
            postList.clear()
        }
        postList = mutableListOf()
    }
}
