package com.example.gomovie

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.login_dialog.view.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        reserveBtn.setOnClickListener {
            //Inflate the dialog with custom view
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.login_dialog, null)
            //AlertDialogBuilder
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)
                .setTitle("Login Form")
            //show dialog
            val mAlertDialog = mBuilder.show()
            mDialogView.dialogLoginBtn.setOnClickListener {
                //dismiss dialog

                val email = mDialogView.dialogEmailEt.text.toString()
                val password = mDialogView.dialogPasswEt.text.toString()
                val db = DatabaseHelperLogin(this, null)
                val cursor = db.getUser(email, password)
                if (cursor?.count!! > 0) {
                    if (cursor?.moveToFirst()!!) {
                        val intent = Intent(this, Selection::class.java)
                        intent.putExtra("Email", email)
                        startActivity(intent)
                    }
                }

                else{
                    Toast.makeText(this, "Wrong username or password!", Toast.LENGTH_SHORT).show()
                    mDialogView.dialogEmailEt.setText("")
                    mDialogView.dialogPasswEt.setText("")
                }
                mAlertDialog.dismiss()
            }
            //cancel button click of custom layout
            mDialogView.dialogCancelBtn.setOnClickListener {
                mAlertDialog.dismiss()
            }

            mDialogView.dialogRegisterBtn.setOnClickListener{
                val intent = Intent(this, Register::class.java)
                startActivity(intent)
            }
        }
    }
}
