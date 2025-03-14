package edu.basic.kyndrylapp.remote

import edu.basic.kyndrylapp.dto.ListadoPerros
import retrofit2.http.GET
import retrofit2.http.Path


//aquí dentro, declaramos el método que comunican con el api de perros
interface PerroService {

    @GET("api/breed/{raza}/images")
    suspend fun listadoDePerrosPorRaza (@Path("raza") raza:String): ListadoPerros
}