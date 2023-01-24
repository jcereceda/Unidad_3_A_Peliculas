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

class AccionAdapter(private val peliculas: ArrayList<Pelicula>,
                    val onClick: (Pelicula) -> Unit
): RecyclerView.Adapter<AccionAdapter.ViewHolderAccion>()
{
    inner class ViewHolderAccion(var view: View):RecyclerView.ViewHolder(view){
        val img = view.findViewById<ImageView>(R.id.cartel)
        fun bindItems(data: Pelicula) {
            Picasso.get().load(data.linkFoto).into(img)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderAccion {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_block_drama, parent, false)
        return ViewHolderAccion(view)
    }

    override fun onBindViewHolder(holder: ViewHolderAccion, position: Int) {
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

}