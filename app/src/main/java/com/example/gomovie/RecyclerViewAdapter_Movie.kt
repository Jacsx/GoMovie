package com.example.gomovie

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.login_dialog.view.*

class RecyclerViewAdapter_Movie (val movies: ArrayList<MovieCard>, val callback:AdapterCallback) : RecyclerView.Adapter<RecyclerViewAdapter_Movie.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieName : TextView = itemView.findViewById(R.id.movieName)
        val movieButton : Button = itemView.findViewById(R.id.movie_button)
        val movieImg : ImageView = itemView.findViewById(R.id.movieImage)
    }

    override fun getItemCount() = movies.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        val view: View = LayoutInflater.from(parent?.context).inflate(R.layout.recyclerview_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter_Movie.ViewHolder, position: Int) {
        holder.movieName.text = movies[position].movieName
        holder.movieImg.setImageResource(movies[position].getmovieImgId())
        holder.movieButton.setOnClickListener{
            callback.onClickCallback(movies[position])
            //Inflate the dialog with custom view

        }
    }

    interface AdapterCallback {
        fun onClickCallback(movieCard: MovieCard)
    }

}
