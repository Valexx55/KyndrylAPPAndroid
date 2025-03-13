package edu.basic.kyndrylapp

import retrofit2.http.GET

interface ProductoService {

    @GET("miseon920/json-api/products")
    suspend fun obtenerListadoProductos ():ListadoProductos
}