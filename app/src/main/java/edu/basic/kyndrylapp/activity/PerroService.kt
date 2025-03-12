package edu.basic.kyndrylapp.activity

import edu.basic.kyndrylapp.ListadoPerros
import retrofit2.http.GET


//aquí dentro, declaramos el método que comunican con el api de perros
interface PerroService {

    @GET("api/breed/{raza}/images")
    suspend fun listadoDePerrosPorRaza (raza:String): ListadoPerros
}