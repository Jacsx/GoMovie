package com.example.gomovie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter_Cinema (val cinema: ArrayList<CinemaCard>, val callback:AdapterCallback) : RecyclerView.Adapter<RecyclerViewAdapter_Cinema.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val cinemaName : TextView = itemView.findViewById(R.id.cinemaName)
            val cinemaButton : Button = itemView.findViewById(R.id.cinema_button)
            val cinemaImg : ImageView = itemView.findViewById(R.id.cinemaImage)
        }

        override fun getItemCount() = cinema.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
            val view: View = LayoutInflater.from(parent?.context).inflate(R.layout.recyclerview_cinema, parent, false)

            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: RecyclerViewAdapter_Cinema.ViewHolder, position: Int) {
            holder.cinemaName.text = cinema[position].cinemaName
            holder.cinemaImg.setImageResource(cinema[position].getCinemaImgId())
            holder.cinemaButton.setOnClickListener{
                callback.onClickCallback(cinema[position])

            }

    }

    interface AdapterCallback {
        fun onClickCallback(cinemaCard: CinemaCard)
    }
}
