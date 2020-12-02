package com.example.bread

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bread.model.Post
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_item_post.view.*
import kotlinx.android.synthetic.main.post_item.view.*

class RecyclerPostAdapter(private val context: Context, private val posts: MutableList<Post?>) :
    RecyclerView.Adapter<RecyclerPostAdapter.ViewHolder>() {


    companion object {
        val TAG = "RecyclerPostAdapter"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)

        return ViewHolder(view)
    }


    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = posts[position]
        val picasso = Picasso.get()
        picasso.load(post?.imageUrl).into(holder.foto)
        holder.titulo.text = post?.nomeProduto
        holder.categoria.text = post?.categoria
        holder.descricao.text = post?.descricao
        holder.name.text = post?.nome
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val foto: ImageView = itemView.postImage
        val titulo: TextView = itemView.nomeProduto
        val categoria: TextView = itemView.textViewCategoria
        val descricao: TextView = itemView.textViewDescricao
        val name: TextView = itemView.textViewName
    }
}

