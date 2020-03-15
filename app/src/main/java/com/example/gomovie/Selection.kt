package com.example.gomovie

import android.app.AlertDialog
import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_addams_movie.view.*
import kotlinx.android.synthetic.main.activity_cathay_ciname.view.*
import kotlinx.android.synthetic.main.activity_gvcinema.view.*
import kotlinx.android.synthetic.main.activity_joker_movie.view.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.activity_male_movie.view.*
import kotlinx.android.synthetic.main.activity_selection.*
import kotlinx.android.synthetic.main.activity_shaw_cinema.view.*
import kotlinx.android.synthetic.main.activity_terminator_movie.view.*
import kotlinx.android.synthetic.main.login_dialog.view.*
import kotlinx.android.synthetic.main.recyclerview_movie.*

class Selection : AppCompatActivity(), RecyclerViewAdapter_Cinema.AdapterCallback, RecyclerViewAdapter_Movie.AdapterCallback {

    var email:String = ""
    override fun onClickCallback(movieCard: MovieCard) {
        Log.d("movieName", movieCard.movieName)
        when (movieCard.movieName){
            "Joker" -> {
                val mDialogView = LayoutInflater.from(this).inflate(R.layout.activity_joker_movie, null)
                //AlertDialogBuilder
                val mBuilder = AlertDialog.Builder(this)
                    .setView(mDialogView)
                //show dialog
                val mAlertDialog = mBuilder.show()
                mDialogView.jokerReserve.setOnClickListener {
                    val intent = Intent(this, ReserveTicket::class.java)
                    intent.putExtra("Movie", movieCard.movieName )
                    intent.putExtra("Email", email)
                    startActivity(intent)

                }
                //cancel button click of custom layout
                mDialogView.jokerCancel.setOnClickListener {
                    mAlertDialog.dismiss()
                }
            }

            "Maleficient: Mistress of Evil" -> {
                val mDialogView = LayoutInflater.from(this).inflate(R.layout.activity_male_movie, null)
                //AlertDialogBuilder
                val mBuilder = AlertDialog.Builder(this)
                    .setView(mDialogView)
                //show dialog
                val mAlertDialog = mBuilder.show()
                mDialogView.malReserve.setOnClickListener {
                    val intent = Intent(this, ReserveTicket::class.java)
                    intent.putExtra("Movie", movieCard.movieName )
                    intent.putExtra("Email", email)
                    startActivity(intent)

                }
                //cancel button click of custom layout
                mDialogView.malCancel.setOnClickListener {
                    mAlertDialog.dismiss()
                }

            }

            "Terminator: Dark Fate" -> {
                val mDialogView = LayoutInflater.from(this).inflate(R.layout.activity_terminator_movie, null)
                //AlertDialogBuilder
                val mBuilder = AlertDialog.Builder(this)
                    .setView(mDialogView)
                //show dialog
                val mAlertDialog = mBuilder.show()
                mDialogView.terReserve.setOnClickListener {
                    val intent = Intent(this, ReserveTicket::class.java)
                    intent.putExtra("Movie", movieCard.movieName )
                    intent.putExtra("Email", email)
                    startActivity(intent)

                }
                //cancel button click of custom layout
                mDialogView.terCancel.setOnClickListener {
                    mAlertDialog.dismiss()
                }

            }

            "The Addams Family" -> {
                val mDialogView = LayoutInflater.from(this).inflate(R.layout.activity_addams_movie, null)
                //AlertDialogBuilder
                val mBuilder = AlertDialog.Builder(this)
                    .setView(mDialogView)
                //show dialog
                val mAlertDialog = mBuilder.show()
                mDialogView.addamsReserve.setOnClickListener {
                    val intent = Intent(this, ReserveTicket::class.java)
                    intent.putExtra("Movie", movieCard.movieName )
                    intent.putExtra("Email", email)
                    startActivity(intent)

                }
                //cancel button click of custom layout
                mDialogView.addamsCancel.setOnClickListener {
                    mAlertDialog.dismiss()
                }
            }
        }
    }

    override fun onClickCallback(cinemaCard: CinemaCard) {
        Log.d("cinemaName", cinemaCard.cinemaName)
        when (cinemaCard.cinemaName){
            "Cathay" -> {
                val mDialogView = LayoutInflater.from(this).inflate(R.layout.activity_cathay_ciname, null)
                //AlertDialogBuilder
                val mBuilder = AlertDialog.Builder(this)
                    .setView(mDialogView)
                //show dialog
                val mAlertDialog = mBuilder.show()
                mDialogView.cathayReserve.setOnClickListener {
                    val intent = Intent(this, ReserveTicket::class.java)
                    intent.putExtra("Cinema",cinemaCard.cinemaName )
                    intent.putExtra("Email", email)
                    startActivity(intent)

                }
                //cancel button click of custom layout
                mDialogView.cathayCancel.setOnClickListener {
                    mAlertDialog.dismiss()
                }
            }
            "Shaw" -> {
                val mDialogView = LayoutInflater.from(this).inflate(R.layout.activity_shaw_cinema, null)
                //AlertDialogBuilder
                val mBuilder = AlertDialog.Builder(this)
                    .setView(mDialogView)
                //show dialog
                val mAlertDialog = mBuilder.show()
                mDialogView.shawReserve.setOnClickListener {
                    val intent = Intent(this, ReserveTicket::class.java)
                    intent.putExtra("Cinema",cinemaCard.cinemaName )
                    intent.putExtra("Email", email)
                    startActivity(intent)

                }
                //cancel button click of custom layout
                mDialogView.shawCancel.setOnClickListener {
                    mAlertDialog.dismiss()
                }
            }

            "Golden Village" -> {
                val mDialogView = LayoutInflater.from(this).inflate(R.layout.activity_gvcinema, null)
                //AlertDialogBuilder
                val mBuilder = AlertDialog.Builder(this)
                    .setView(mDialogView)
                //show dialog
                val mAlertDialog = mBuilder.show()
                mDialogView.gvReserve.setOnClickListener {
                    val intent = Intent(this, ReserveTicket::class.java)
                    intent.putExtra("Cinema",cinemaCard.cinemaName )
                    intent.putExtra("Email", email)
                    startActivity(intent)

                }
                //cancel button click of custom layout
                mDialogView.gvCancel.setOnClickListener {
                    mAlertDialog.dismiss()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selection)

        email = getIntent().getStringExtra("Email")

        val movies = ArrayList<MovieCard>()
        movies.add(MovieCard("Joker", R.drawable.jokermovie))
        movies.add(MovieCard("Maleficient: Mistress of Evil", R.drawable.maleficent))
        movies.add(MovieCard("Terminator: Dark Fate", R.drawable.terminator))
        movies.add(MovieCard("The Addams Family", R.drawable.addamsfamily))

        val cinema = ArrayList<CinemaCard>()
        cinema.add(CinemaCard("Shaw", R.drawable.shaw))
        cinema.add(CinemaCard("Golden Village", R.drawable.gv))
        cinema.add(CinemaCard("Cathay", R.drawable.cathay))

        for(i in 1..6){
            movies.toArray()
        }

        recyclerViewMovie.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        recyclerViewMovie.adapter = RecyclerViewAdapter_Movie(movies, this)

        for(i in 1..3) {
            cinema.toArray()
        }

        recyclerViewCinema.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        recyclerViewCinema.adapter = RecyclerViewAdapter_Cinema(cinema, this)
        }


}

