package com.example.musixster

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ExploreAdapter(val songs: SongData): RecyclerView.Adapter<CustomViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.explore_item, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        val artists = songs.artist
        val songName = songs.title
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

}

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view)