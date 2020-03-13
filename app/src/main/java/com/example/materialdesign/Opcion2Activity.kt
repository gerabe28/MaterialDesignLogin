package com.example.materialdesign

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

class Opcion2Activity : AppCompatActivity() {


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

        descriptionCiudad = findViewById(R.id.descriptionCiudad)
        titleCiudad = findViewById(R.id.titleCiudad)
        imageCiudad = findViewById(R.id.imageCard)
        txtfalseView2 = findViewById(R.id.txtfalseView2)
        txtBool = findViewById(R.id.txtBool)
        txtPrice = findViewById(R.id.txtPrice)


           getCurrentData()
    }




        internal fun getCurrentData() {

            val retrofit = Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = retrofit.create(CityServices::class.java)
            val call = service.getCurrentCityData(endpoint)

         call.enqueue(object : Callback<TestResponse> {
                @SuppressLint("WrongConstant")
                override fun onResponse(
                    call: Call<TestResponse>,
                    response: Response<TestResponse>
                ) {
                    if (response.code() == 200) {
                        val TestResponseList = response.body()!!

                        val datosss = TestResponseList.Title


           val arrayCiudad = ArrayList<ListSelectionViewHolder>()

                        val recyclerView: RecyclerView = findViewById(R.id.recycler)

                        for( city in TestResponseList.ciudades!!){

                         //   val arrayCiudad = ArrayList<ListSelectionViewHolder>()

                            arrayCiudad.add(
                                ListSelectionViewHolder(
                                    ""+city.url,
                                    ""+city.nameCity,
                                    ""+city.Descripcion,
                                    ""+city.Fecha,
                                    ""+city.datito_boolean,
                                    ""+city.Precio
                                )
                            )
                            recyclerView.layoutManager =
                            LinearLayoutManager(this@Opcion2Activity, LinearLayout.VERTICAL, false)

                            val adapter = ListSelectionRecyclerViewAdapter(arrayCiudad)

                            recyclerView.adapter = adapter



                            Log.d("tag","AQUI "+city.datito_boolean)
                        }


                     Log.d("tag","AQUI "+datosss)
                       /*val img1: String =
                            "https://www.nationalgeographic.com.es/medio/2020/01/28/malang-indonesia-a-colourful-city_cda40d9f_1500x1000.jpg"

                        //       val StringDato = CityResponse.Descripcion
                        //       ciudadesData!!.text = StringDato

                        val recyclerView: RecyclerView = findViewById(R.id.recycler)
                        recyclerView.layoutManager =
                            LinearLayoutManager(this@Opcion2Activity, LinearLayout.VERTICAL, false)
                        val city = ArrayList<ListSelectionViewHolder>()
                       /* city.add(
                            ListSelectionViewHolder(
                                img1,
                                "a"+datosss,
                                "b"/*+CityResponse.Descripcion*/,
                                "c"/*+CityResponse.fecha!!*/,
                                "d"/*+CityResponse.datitoBolean!!*/,
                                "e"/*+CityResponse.fecha!!*/
                            )
                        )*/
                        val adapter = ListSelectionRecyclerViewAdapter(city)

                        recyclerView.adapter = adapter
*/
                            }

                }


                override fun onFailure(call: Call<TestResponse>, t: Throwable) {
                    ciudadesData!!.text = t.message
                }
            })
        }


        companion object {

            var BaseUrl = "https://private-50caf3-gerabe28.apiary-mock.com/"
            var endpoint = "gg"
        }

}



