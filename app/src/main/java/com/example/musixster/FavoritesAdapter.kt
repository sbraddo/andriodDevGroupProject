package com.example.musixster

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FavoritesAdapter(private val entries: MutableList<SongEntry>) : RecyclerView.Adapter<FavoritesAdapter.ViewHolder>() {

    inner class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val fTitle = itemView.findViewById<TextView>(R.id.songName)
        private val fArtist = itemView.findViewById<TextView>(R.id.artistName)

        fun bind(song: SongEntry) {
            fTitle.text = song.title
            fArtist.text = song.artist
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val favoritesView = inflater.inflate(R.layout.favorites_items, parent, false)
        return ViewHolder(favoritesView)
    }

    override fun getItemCount(): Int {
        return entries.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val song = entries[position]
        holder.bind(song)
    }

}