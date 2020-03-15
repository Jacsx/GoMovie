package com.example.gomovie

class CinemaCard {
    var cinemaName:String = ""
    var cinemaImg:Int = 0

    constructor(cinemaName:String, cinemaImg:Int){
        this.cinemaName = cinemaName
        this.cinemaImg = cinemaImg
    }

    fun getCinemaImgId(): Int {
        return cinemaImg
    }

    fun setCinemaImageId(cinemaImg: Int) {
        this.cinemaImg = cinemaImg
    }
}