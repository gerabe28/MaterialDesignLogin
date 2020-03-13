package com.example.materialdesign
/*
class FalsePruebaActivity {
}
*/
/*
package com.example.materialdesign
*/
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_opcion2.*
import kotlinx.android.synthetic.main.content_item.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class Opcion2ActivityAAAAAAAA : AppCompatActivity() {


    private var titleCiudad: TextView? = null
    private var imageCiudad: ImageView? = null
    private var floating_action_button: FloatingActionButton? = null
    private var txtfalseView: CardView? = null
    private var txtfalseView2: TextView? = null
    private var txtBool : TextView? = null
    private var txtPrice: TextView? = null
    private var ciudadesData: TextView? = null
    private var descriptionCiudad : TextView? = null
    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opcion2)

        //     descriptionCiudad = findViewById(R.id.descriptionCiudad)

        //ciudadesData = findViewById(R.id.titleCiudad)

        descriptionCiudad = findViewById(R.id.descriptionCiudad)
        titleCiudad = findViewById(R.id.titleCiudad)
        imageCiudad = findViewById(R.id.imageCard)
        txtfalseView2 = findViewById(R.id.txtfalseView2)
        txtBool = findViewById(R.id.txtBool)
        txtPrice = findViewById(R.id.txtPrice)


        /*findViewById<View>(R.id.button).setOnClickListener { getggData() }*/
        getCurrentData()
    }
//---------------
    /*   val img1: String = "https://www.nationalgeographic.com.es/medio/2020/01/28/malang-indonesia-a-colourful-city_cda40d9f_1500x1000.jpg"
    val img2: String = "https://www.nationalgeographic.com.es/medio/2020/01/28/danum-valley-borneo-malaysia_76d14240_1500x1000.jpg"

    val recyclerView: RecyclerView = findViewById(R.id.recycler)
    recyclerView.layoutManager = LinearLayoutManager(this,LinearLayout.VERTICAL,false)
    val albums = ArrayList<ListSelectionViewHolder>()

    albums.add(ListSelectionViewHolder( img1,"Cusco","Cuzco es una ciudad de los Andes peruanos que fue la capital del Imperio Inca y es conocida por sus restos arqueológicos y la arquitectura colonial española.","2012-02-02 \n ta carito",false,240))
    albums.add(ListSelectionViewHolder(img2,"Iquitos","Conocida también como Ciudad de Iquitos, es la ciudad capital de la provincia de Maynas y el departamento de Loreto.","2005-12-14 \n\n compra casero",true,290))

    val adapter = ListSelectionRecyclerViewAdapter(albums)

    recyclerView.adapter = adapter
}
}*/
//---------------


    /*
internal fun getggData() {

    val retrofit = Retrofit.Builder()
        .baseUrl(BaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(CityServices::class.java)
    val call = service.getCurrentData()
    call.enqueue(object : Callback<TestResponse> {
        override fun onResponse(call: Call<TestResponse>, response: Response<TestResponse>) {
            if (response.isSuccessful) {
                try {
                    ciudadesData!!.text = response.body()!!.ciudades!!.size.toString()

                    //recycler.adapter = newAdapterToCreate(response.body()!!.ciudades)
                }catch (e: Exception){
                    ciudadesData!!.text = "Juan cabro"
                }

            }
        }

        override fun onFailure(call: Call<TestResponse>, t: Throwable) {
            ciudadesData!!.text = t.message
        }
    })
}
*/

    /*
--------
    crear un espacio para cada uno
    crear primero que traigan los valores
    luego añadirlos al array "add(album)"
    finalizar el adapter
--------

     */
    internal fun getCurrentData() {

        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(CityServices::class.java)
        val call = service.getCurrentCityData(endpoint)

        //    CAMBIAR EL CALLBACK POR EL TESTRESPONSE

        call.enqueue(object : Callback<TestResponse> {
            @SuppressLint("WrongConstant")
            override fun onResponse(
                call: Call<TestResponse>,
                response: Response<TestResponse>
            ) {
                if (response.code() == 200) {
                    val TestResponseList = response.body()!!

                    val datosss = TestResponseList.Title

                    for(city in TestResponseList.ciudades!!){
                        val a =  city.nameCity
                        val b =  city.Descripcion
                        val c =   city.Fecha
                        val d =  city.Precio
                        val e =  city.datito_boolean
                        val f =  city.url
                    }



                    /* val descripcionJson = CityResponse.Descripcion
                     val nameJson = CityResponse.nameCity
                     val datoBoolJson = CityResponse.datitoBolean.toString()
                     val precioJson = CityResponse.Precio.toString()
                     val fechaJson = CityResponse.Fecha
                     val urlJson = CityResponse.url

                     Log.d("tag","respuesta: " +CityResponse.nameCity)
*/
                    Log.d("tag","AQUI "+datosss)
                    //descriptionCiudad!!.text = descripcionJson
                    // titleCiudad!!.text = nameJson
                    //txtBool!!.text = datoBoolJson.toString()
                    //txtPrice!!.text = precioJson.toString()
                    //txtfalseView2!!.text = fechaJson

                    /* val stringBuilder = CityResponse.url!! +
                    "\n" +
                    "Ciudad: " +
                    CityResponse.nameCity!! +
                    "\n" +
                    "Descripcion " +
                    CityResponse.descripcion!! +
                    "\n" +
                    "Fecha" +
                    CityResponse.fecha!! +
                    "\n" +
                    "Boolean" +
                    CityResponse.datitoBolean!! +
                    "\n" +
                    "Precio" +
                    CityResponse.precio!!

            ciudadesData!!.text = stringBuilder*/
                    val img1: String =
                        "https://www.nationalgeographic.com.es/medio/2020/01/28/malang-indonesia-a-colourful-city_cda40d9f_1500x1000.jpg"

                    //       val StringDato = CityResponse.Descripcion
                    //       ciudadesData!!.text = StringDato

                    val recyclerView: RecyclerView = findViewById(R.id.recycler)
                    recyclerView.layoutManager =
                        LinearLayoutManager(this@Opcion2ActivityAAAAAAAA, LinearLayout.VERTICAL, false)
                    val albums = ArrayList<ListSelectionViewHolder>()
                    albums.add(
                        ListSelectionViewHolder(
                            img1,
                            "a"+datosss,
                            "b"/*+CityResponse.Descripcion*/,
                            "c"/*+CityResponse.fecha!!*/,
                            "d"/*+CityResponse.datitoBolean!!*/,
                            "e"/*+CityResponse.fecha!!*/
                        )
                    )
                    val adapter = ListSelectionRecyclerViewAdapter(albums)

                    recyclerView.adapter = adapter
                }
            }


            override fun onFailure(call: Call<TestResponse>, t: Throwable) {
                ciudadesData!!.text = t.message
            }
        })
    }

    /*--------mind-----------
    val img1: String =
        "https://www.nationalgeographic.com.es/medio/2020/01/28/malang-indonesia-a-colourful-city_cda40d9f_1500x1000.jpg"

    //       val StringDato = CityResponse.Descripcion
    //       ciudadesData!!.text = StringDato

    val recyclerView: RecyclerView = findViewById(R.id.recycler)
    recyclerView.layoutManager =
    LinearLayoutManager(this@Opcion2Activity, LinearLayout.VERTICAL, false)
    val albums = ArrayList<ListSelectionViewHolder>()
    albums.add(
    ListSelectionViewHolder(
    img1,
    "a"+CityResponse.nameCity,
    //  ciudadesData.toString(),
    "b"/*+CityResponse.Descripcion*/,
    "c"/*+CityResponse.fecha!!*/,
    "d"/*+CityResponse.datitoBolean!!*/,
    "e"/*+CityResponse.fecha!!*/
    )
    )
    val adapter = ListSelectionRecyclerViewAdapter(albums)

    recyclerView.adapter = adapter

    ---------endMind---------
    */
    companion object {

        var BaseUrl = "https://private-50caf3-gerabe28.apiary-mock.com/"
        var endpoint = "gg"
    }

}



/*
val img1: String = "https://www.nationalgeographic.com.es/medio/2020/01/28/malang-indonesia-a-colourful-city_cda40d9f_1500x1000.jpg"
val img2: String = "https://www.nationalgeographic.com.es/medio/2020/01/28/danum-valley-borneo-malaysia_76d14240_1500x1000.jpg"
    val recyclerView: RecyclerView = findViewById(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(this,LinearLayout.VERTICAL,false)
        val albums = ArrayList<ListSelectionViewHolder>()

        //ArrayList<Tittle>().add(Tittle("CIUDADES"))

        albums.add(ListSelectionViewHolder( img1,"Cusco","Cuzco es una ciudad de los Andes peruanos que fue la capital del Imperio Inca y es conocida por sus restos arqueológicos y la arquitectura colonial española.","2012-02-02 \n ta carito",false,240))
        albums.add(ListSelectionViewHolder(img2,"Iquitos","Conocida también como Ciudad de Iquitos, es la ciudad capital de la provincia de Maynas y el departamento de Loreto.","2005-12-14 \n\n compra casero",true,290))

        val adapter = ListSelectionRecyclerViewAdapter(albums)

        recyclerView.adapter = adapter
  }

}
*/