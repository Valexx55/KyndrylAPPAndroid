package edu.basic.kyndrylapp.remote

import edu.basic.kyndrylapp.dto.ListadoProductos
import retrofit2.http.GET

interface ProductoService {

    @GET("miseon920/json-api/products")
    suspend fun obtenerListadoProductos (): ListadoProductos
}