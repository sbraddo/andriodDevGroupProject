package com.example.musixster

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.GsonBuilder
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

// TODO:
// 1. DISPLAY as many songs as possible from billboardAPI
// 2. OnClick -> save songs to database

class ExploreFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//        onCreateView()
//        fetchJSON()
//    }

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

                activity?.runOnUiThread {  }
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failure to execute request")
            }
        })


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.d("TAG","Create View")
        return inflater.inflate(R.layout.fragment_explore, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("TAG","View Created")
        fetchJSON()
    }
}

class Content(val type: String)