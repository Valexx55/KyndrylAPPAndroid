package edu.basic.kyndrylapp

//el JSON que yo Reibo del API, hecho Clase de Kotlin
data class ListadoPerros(
    val message: List<String>,
    val status: String
)