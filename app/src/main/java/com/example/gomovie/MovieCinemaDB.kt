package com.example.gomovie

class MovieCinemaDB{

    var movieTitle: String? = null
    var movieImage: String? = null
    var cinemaName : String? = null
    var cinemaImage: String? = null
    var movieTime : String? = null

    constructor(movieTitle: String, movieImage:String, cinemaName: String, cinemaImage: String, movieTime: String) {
        this.movieTitle = movieTitle
        this.movieImage  =  movieImage
        this.cinemaName = cinemaName
        this.cinemaImage = cinemaImage
        this.movieTime = movieTime

    }
}