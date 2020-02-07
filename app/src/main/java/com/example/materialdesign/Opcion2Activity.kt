package com.example.materialdesign

import android.annotation.SuppressLint
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class Opcion2Activity : AppCompatActivity() {


    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opcion2)

val img1: String = "https://www.nationalgeographic.com.es/medio/2020/01/28/malang-indonesia-a-colourful-city_cda40d9f_1500x1000.jpg"
val img2: String = "https://www.nationalgeographic.com.es/medio/2020/01/28/danum-valley-borneo-malaysia_76d14240_1500x1000.jpg"
    val recyclerView: RecyclerView = findViewById(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(this,LinearLayout.VERTICAL,false)
        val albums = ArrayList<ListSelectionViewHolder>()


        albums.add(ListSelectionViewHolder( img1,"Cusco","Cuzco es una ciudad de los Andes peruanos que fue la capital del Imperio Inca y es conocida por sus restos arqueológicos y la arquitectura colonial española."))
        albums.add(ListSelectionViewHolder(img2,"Iquitos","Conocida también como Ciudad de Iquitos, es la ciudad capital de la provincia de Maynas y el departamento de Loreto."))


        val adapter = ListSelectionRecyclerViewAdapter(albums)

        recyclerView.adapter = adapter
    }

}
