package com.example.gomovie

class MovieCard {
    var movieName:String = ""
    var movieImg:Int = 0

    constructor(movieName:String, movieImg:Int){
        this.movieName = movieName
        this.movieImg = movieImg
    }

    fun getmovieImgId(): Int {
        return movieImg
    }

    fun setmovieImgId(movieImg: Int) {
        this.movieImg = movieImg
    }
}