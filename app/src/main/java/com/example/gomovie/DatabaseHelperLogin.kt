package com.example.gomovie

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log


class DatabaseHelperLogin (context: Context,
                  factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME,
        factory, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_PRODUCTS_TABLE = ("CREATE TABLE " +
                TABLE_NAME + "("
                + COLUMN_USERID + " integer primary key autoincrement not null,"
                + COLUMN_EMAIL + " text not null," +
                COLUMN_PASSWORD + " text not null)")
        db.execSQL(CREATE_PRODUCTS_TABLE)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }
    fun addUser(user: LoginDB) {
        val values = ContentValues()
        values.put(COLUMN_EMAIL, user.userEmail)
        values.put(COLUMN_PASSWORD, user.userPassword)
        Log.d("User", user.userEmail)
        Log.d("User", user.userPassword)
        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getUser(username:String, password:String): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME WHERE $COLUMN_EMAIL='$username' AND $COLUMN_PASSWORD='$password'", null)
    }


    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "Login.db"
        val TABLE_NAME = "UserData"
        val COLUMN_USERID = "User_ID"
        val COLUMN_EMAIL = "Email"
        val COLUMN_PASSWORD = "Password"
    }
}