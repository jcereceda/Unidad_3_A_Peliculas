package com.utad.unidad_3_a_peliculas.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.utad.unidad_3_a_peliculas.Pelicula
import com.utad.unidad_3_a_peliculas.R

class ComediaAdapter(private val peliculas: ArrayList<Pelicula>,
                     val onClick: (Pelicula) -> Unit): RecyclerView.Adapter<ComediaAdapter.ViewHolderComedia>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderComedia {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_block_drama, parent, false)
        return ViewHolderComedia(view)
    }

    override fun onBindViewHolder(holder: ViewHolderComedia, position: Int) {
        val data = peliculas?.get(position)
        if (data != null) {
            holder.bindItems(data)
        }
        var elemento = holder.itemView.findViewById<View>(R.id.peli) as ConstraintLayout
        elemento.setOnClickListener {
            if (data != null) {
                onClick(data)
            }
        }
    }

    override fun getItemCount(): Int {
       return peliculas.size
    }

    inner class ViewHolderComedia(var view: View):RecyclerView.ViewHolder(view){
        val img = view.findViewById<ImageView>(R.id.cartel)
        fun bindItems(data: Pelicula) {
            Picasso.get().load(data.linkFoto).into(img)
        }
    }
}