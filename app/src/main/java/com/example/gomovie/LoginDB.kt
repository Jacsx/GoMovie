package com.example.gomovie

class LoginDB {

    var userEmail: String? = null
    var userPassword: String? = null

    constructor(userEmail: String, userPassword: String){

        this.userEmail = userEmail
        this.userPassword = userPassword
    }
}