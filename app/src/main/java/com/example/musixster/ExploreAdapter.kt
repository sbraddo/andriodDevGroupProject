package com.example.musixster

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL


class ExploreAdapter(private val chart: Chart): RecyclerView.Adapter<ExploreAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val eTitle : TextView
        private val eArtist : TextView
        private val eCover : ImageView

        init{
//            itemView.setOnLongClickListener(object : View.OnLongClickListener {
//                override fun onLongClick(v: View?): Boolean {
//                    // Save to favorites database
//                    Toast.makeText(this, "SONG SAVED", Toast.LENGTH_SHORT).show()
//
//                    return true
//                }
//            })
            eTitle = itemView.findViewById<TextView>(R.id.exploreSongName)
            eArtist = itemView.findViewById<TextView>(R.id.exploreArtistName)
            eCover = itemView.findViewById<ImageView>(R.id.exploreCover)
        }

        fun bind(entries: ChartEntries){
            eTitle.text = entries.title
            eArtist.text = entries.artist
            eCover.setImageBitmap(getBitmapFromURL(entries.cover))


        }



    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExploreAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.explore_item, parent, false)
        return ViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: ExploreAdapter.ViewHolder, position: Int) {
        val bindEntry = chart.entries[position]
        holder.bind(bindEntry)


    }

    override fun getItemCount(): Int {
        return chart.entries.size
    }

    fun getBitmapFromURL(src: String?): Bitmap? {
        return try {
            Log.e("src", src!!)
            val url = URL(src)
            val connection = url.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            val input = connection.inputStream
            val myBitmap = BitmapFactory.decodeStream(input)
            Log.e("Bitmap", "returned")
            myBitmap
        } catch (e: IOException) {
            e.printStackTrace()
            Log.e("Exception", e.message!!)
            null
        }
    }

}

