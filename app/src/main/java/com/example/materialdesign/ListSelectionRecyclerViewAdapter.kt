package com.example.materialdesign

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ListSelectionRecyclerViewAdapter(var list: ArrayList<ListSelectionViewHolder>): RecyclerView.Adapter<ListSelectionRecyclerViewAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      val v = LayoutInflater.from(parent.context).inflate(R.layout.content_item,parent,false )
        return ViewHolder(v)
    }


    override fun getItemCount(): Int {
       return list.size
           }

    override fun onBindViewHolder(holder: ListSelectionRecyclerViewAdapter.ViewHolder, position: Int) {
       holder.bindItems(list[position])
           }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
          fun bindItems(data:ListSelectionViewHolder){
              val titleCiudad: TextView = itemView.findViewById(R.id.titleCiudad)
              val descriptionCiudad: TextView = itemView.findViewById(R.id.descriptionCiudad)
              val imageCiudad: ImageView = itemView.findViewById(R.id.imageCard)

              titleCiudad.text=data.nameCity
              descriptionCiudad.text=data.descripcion
              Glide.with(itemView.context).load(data.imageCiudad).into(imageCiudad)

        }
    }

}