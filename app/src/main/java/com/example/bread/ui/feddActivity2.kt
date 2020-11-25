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
    lateinit var postList: MutableList<Post>
    val recyclerPostAdapter = RecyclerPostAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fedd2)


        //banco
        mStorageRef = FirebaseStorage.getInstance().getReference();
        database = FirebaseDatabase.getInstance()
        databaseReference = database.getReference()

        val query = databaseReference.child("posts")

        val listener = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
              snapshot.children.forEach {
                  val imageUrl = it.child("imageUrl").getValue().toString()
                  val nomeProduto = it.child("nomeProduto").getValue().toString()
                  val categoria = it.child("categoria").getValue().toString()
                  val descricao = it.child("descricao").getValue().toString()


                  postList.add()
              }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }

        query.addListenerForSingleValueEvent(listener)

        val linearLayoutManager = LinearLayoutManager(this)


        recyclerViewFeed.layoutManager = linearLayoutManager
        recyclerViewFeed.setHasFixedSize(true)



        postList = mutableListOf()



        postbutton.setOnClickListener {
            val Intent: Intent = Intent(this, PostActivity::class.java)
            startActivity(Intent)
        }
    }


    fun clearALL(){
        if(postList != null){
            postList.clear()
        }
        postList =   mutableListOf()
    }
}
