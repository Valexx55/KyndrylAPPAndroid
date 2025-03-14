package edu.basic.kyndrylapp.dto

class IMC (val peso:Float, val altura:Float) {

    fun calcularIMC ():Float
    {
        var resutado:Float = 0f

            resutado = this.peso / (this.altura*this.altura)

        return resutado
    }
}