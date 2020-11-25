package com.example.bread

import android.content.Context
import android.nfc.Tag
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.bread.model.Post
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.post_item.view.*

class RecyclerPostAdapter (private val context: Context, private val posts: List<Post> ) : RecyclerView.Adapter<RecyclerPostAdapter.ViewHolder>() {


    companion object {
        val TAG = "RecyclerPostAdapter"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.getContext()).
        inflate(R.layout.post_item, parent, false)

        return ViewHolder(view)
    }


    override fun getItemCount(): Int {
        return posts.size
        Log.d("RecyclerPostAdapter", "Alguma coisa...")
    }

    //val nomeProduto: String, val categoria: String, val descricao: String, val imageUrl: String

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = posts[position]
        val picasso = Picasso.get()
        picasso.load(post.imageUrl).into(holder.foto)
        holder.titulo.text = post.nomeProduto
        holder.categoria.text = post.categoria
        holder.descricao.text = post.descricao
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val foto = itemView.postImage
        val titulo = itemView.nomeProduto
        val categoria = itemView.textViewCategoria
        val descricao = itemView.textViewDescricao

    }

}

