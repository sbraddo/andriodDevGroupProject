package com.example.musixster

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.beust.klaxon.Klaxon
import com.google.gson.GsonBuilder
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONArray
import java.io.IOException



// TODO:
// 1. DISPLAY as many songs as possible from billboardAPI
// 2. OnClick -> save songs to database

class ExploreFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var exploreAdapter: ExploreAdapter
    private lateinit var exploreRV : RecyclerView
    private var entries = mutableListOf<ChartEntries>()
    private lateinit var chartObj : Chart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val jsonBundle: Bundle? = intent.extras

    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.d("TAG","Create View")
        val view = inflater.inflate(R.layout.fragment_explore, container, false)
        val data = arguments
        val jsonData = data!!.get("chartData").toString()
        val chartObj = Klaxon().parse<Chart>(jsonData)
        if (chartObj != null) {
            entries = chartObj.entries.toMutableList()
        }
        val layoutManager = LinearLayoutManager(context)
        val exploreRV = view.findViewById<RecyclerView>(R.id.exploreRV)
        exploreRV.layoutManager = layoutManager
        exploreAdapter =  ExploreAdapter(chartObj!!)
        exploreRV.adapter = exploreAdapter

        return view
    }

    companion object {
        fun newInstance() : ExploreFragment {
            return ExploreFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("TAG","View Created")
        val layoutManager = LinearLayoutManager(context)
        exploreRV = view.findViewById(R.id.exploreRV)
        exploreRV.layoutManager = layoutManager
        exploreRV.setHasFixedSize(true)
        exploreAdapter = ExploreAdapter(chartObj)
        exploreRV.adapter = exploreAdapter

//        fetchItems()
    }


}
