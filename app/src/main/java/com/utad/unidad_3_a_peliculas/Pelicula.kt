package com.utad.unidad_3_a_peliculas

class Pelicula (
    val linkFoto:String,
    val linkFoto2: String,
    var titulo: String,
    var argumento: String,
    var genero: String,
    var year: Int,
    var director: String
): java.io.Serializable  {}