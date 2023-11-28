package com.example.musixster

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore.Audio.Artists
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.musixster.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.GsonBuilder
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.text.FieldPosition
import java.util.Dictionary

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        replaceFragment(TrendingFragment())

        val exploreFragment: Fragment = ExploreFragment()
        val favoritesFragment: Fragment = FavoritesFragment()
        val trendingFragment: Fragment = TrendingFragment()
        val bottomNav: BottomNavigationView = findViewById(R.id.bottomNavigation)

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            var fragment: Fragment = Fragment()
            when (item.itemId) {
                R.id.exploreNav -> {
                    replaceFragment(ExploreFragment())
                    true
                }
                R.id.favoritesNav -> {
                    replaceFragment(FavoritesFragment())
                    true
                }
                R.id.trendingNav -> {
                    replaceFragment(TrendingFragment())
                    true
                }
            }
//            replaceFragment(fragment)
            true
        }
        bottomNav.selectedItemId = R.id.trendingNav


//        fetchJSON()
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransition = fragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.fragmentContainer, fragment)
        fragmentTransition.commit()
    }

    fun fetchJSON(){

        Log.d("TAG","Attempting to fetch JSON")

        val url = "https://billboard-api5.p.rapidapi.com/api/charts/hot-100"
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .get()
            .addHeader("X-RapidAPI-Key", "ddb246746amshe7dbc6c72a11b57p18f205jsn50de5f55b2a9")
            .addHeader("X-RapidAPI-Host", "billboard-api5.p.rapidapi.com")
            .build()
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response?.body?.string()
                println(body)
                Log.d("TAG", "API request successful")


                val gson = GsonBuilder().create()
                val chart = gson.fromJson(body, Chart::class.java)
//                println(chart.toString())

                val jsonBundle = Bundle()
                val explore = ExploreFragment()
                jsonBundle.run {
                    putString("chartData", body)
                }
//                val intent = Intent(this@MainActivity, ExploreFragment::class.java)
//                intent.putExtras(jsonBundle)
//                startActivity(intent)

                explore.arguments = jsonBundle


//                runOnUiThread {
//                    exploreRV.adapter = ExploreAdapter(chart, this@MainActivity)
//                }

//                context?.runOnUiThread {  }
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failure to execute request")

            }
        })

    }
}

class Chart(val curWeek: String, val prevWeek: PrevWeek, val nextWeek: NextWeek, val entries: List<ChartEntries>)

class PrevWeek(val date: String, val url: String)
class NextWeek(val date: String, val url: String)

class ChartEntries(val rank: Int, val title: String, val artist: String, val cover: String, val position: Position)

class Position(val posLastWeek: Int, val peak : Int, val weeksOnChart : Int)