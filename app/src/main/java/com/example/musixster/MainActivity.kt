package com.example.musixster

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.google.gson.GsonBuilder
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         fetchJSON()
    }

    fun fetchJSON(){
        Log.d("TAG","Attempting to fetch JSON")

        val url = "https://billboard-api2.p.rapidapi.com/hot-100?date=2019-05-11&range=1-10"
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .get()
            .addHeader("X-RapidAPI-Key", "ddb246746amshe7dbc6c72a11b57p18f205jsn50de5f55b2a9")
            .addHeader("X-RapidAPI-Host", "billboard-api2.p.rapidapi.com")
            .build()
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response?.body?.string()
                println(body)
                Log.d("TAG", "API request successful")

                val gson = GsonBuilder().create()
                val songs = gson.fromJson(body, SongData::class.java)

//                context?.runOnUiThread {  }
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failure to execute request")
            }
        })


    }
}