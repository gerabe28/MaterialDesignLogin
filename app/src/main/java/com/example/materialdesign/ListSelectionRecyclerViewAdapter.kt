package com.example.materialdesign

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.w3c.dom.Text

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
        // holder.enviarTitulo()
           }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
          @SuppressLint("WrongConstant")
          fun bindItems(data:ListSelectionViewHolder){
              val titleCiudad: TextView = itemView.findViewById(R.id.titleCiudad)
              val descriptionCiudad: TextView = itemView.findViewById(R.id.descriptionCiudad)
              val imageCiudad: ImageView = itemView.findViewById(R.id.imageCard)
              val floating_action_button: FloatingActionButton = itemView.findViewById(R.id.floating_action_button)
              val txtfalseView: CardView = itemView.findViewById(R.id.txtfalseView)
              val txtfalseView2: TextView = itemView.findViewById(R.id.txtfalseView2)
              val txtBool : TextView = itemView.findViewById(R.id.txtBool)
              val txtPrice: TextView = itemView.findViewById(R.id.txtPrice)

              titleCiudad.text=data.nameCity
              descriptionCiudad.text=data.descripcion
              Glide.with(itemView.context).load(data.imageCiudad).into(imageCiudad)
              txtfalseView2.text=data.txtfalseView2
              txtBool.text=data.valorBool.toString()
              txtPrice.text=data.Price.toString()

              floating_action_button.setOnClickListener {

                  if (txtfalseView.visibility == View.INVISIBLE){
                      txtfalseView.visibility = View.VISIBLE
                  }else{
                      txtfalseView.visibility = View.INVISIBLE
                  }
              }

              }
      /*  fun enviarTitulo(data:Tittle){
            val titleTitle: TextView = itemView.findViewById(R.id.txtTitle)

            titleTitle.text=data.titleCiudad
        }*/

          }
    }

