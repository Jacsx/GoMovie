package com.example.gomovie

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_register.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

class Register : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        var TAG = Register::class.java.simpleName
        val pwdError = findViewById<TextView>(R.id.pwdMatch)
        lateinit var emailPattern: String

        cancelBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        var edtEmail = findViewById(R.id.EmailEt) as EditText

        regBtn.setOnClickListener {
            emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.+[a-z]+"

            if (edtEmail.text.toString().trim { it <= ' ' }.matches(emailPattern.toRegex())) {
                emailError.setText("OK")

                if (PasswEt.text.toString() != PasswEtConfirm.text.toString())
                    pwdError.setText("Passwords do not Match!")

                else {
                    val userEmail = EmailEt.text.toString()
                    val password = PasswEt.text.toString()
                    val newUser = LoginDB(userEmail, password)

                    val database = DatabaseHelperLogin(this, null)
                    database.addUser(newUser)
                    val intent = Intent(this@Register, MainActivity::class.java)
                    startActivity(intent)
                }
            }
            else
                emailError.setText("Please Enter a Valid Email")
        }
    }
}


