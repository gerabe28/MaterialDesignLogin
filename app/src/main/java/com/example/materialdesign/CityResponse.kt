package com.example.materialdesign

import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.ArrayList


class TestResponse{
    @SerializedName("Title")
    var Title: String =""
    @SerializedName("ciudades")
    var ciudades: ArrayList<CityResponse>? = null
}
class CityResponse {
    @SerializedName("url")
    var url: String? = null
    @SerializedName("nameCity")
    var nameCity: String? = null
    @SerializedName("Descripcion")
    var Descripcion: String? = null
    @SerializedName("Fecha")
    var Fecha: String? = null
    @SerializedName("datito_boolean")
    var datito_boolean: Boolean? = null
    @SerializedName("Precio")
    var Precio: Int? = null





    // @SerializedName("weather")
    // var weather = ArrayList<Weather>()
   /* @SerializedName("dt")
    var dt: Float = 0.toFloat()
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("name")
    var name: String? = null
    @SerializedName("cod")
    var cod: Float = 0.toFloat()
*/
}
//done
class url {
    @SerializedName("url")
    var url: String? = null
}

//done
class nameCity {
    @SerializedName("nameCity")
    var nameCity: String? = null
}

//done
class Descripcion {
    @SerializedName("Descripcion")
    var Descripcion: String? = null
}

//done
class Fecha {
    @SerializedName("Fecha")
    var Fecha: Date? = null
}

//done
class datito_bolean {
    @SerializedName("datito_boolean")
    var datito_boolean: Boolean? = null
}
//done
class Precio {
    @SerializedName("Precio")
    var Precio: Int = 0

}


/*
class Coord {
    @SerializedName("lon")
    var lon: Float = 0.toFloat()
    @SerializedName("lat")
    var lat: Float = 0.toFloat()
}*/