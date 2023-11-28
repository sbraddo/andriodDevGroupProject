package com.example.musixster

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

// DISPLAY saved songs here

/**
 * A simple [Fragment] subclass.
 * Use the [FavoritesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavoritesFragment : Fragment() {
    // songs here should be the database of saved songs
    private val songs = mutableListOf<SongEntry>()
    private lateinit var itemAdapter: FavoritesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchItems()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favorites, container, false)
        val layoutManager = LinearLayoutManager(context)
        val itemRV = view.findViewById<RecyclerView>(R.id.favoritesRV)
        itemRV.setHasFixedSize(true)
        itemAdapter = FavoritesAdapter(songs)
        itemRV.adapter = itemAdapter
        return view
    }

    companion object {
        fun newInstance(): FavoritesFragment {
            return FavoritesFragment()
        }
    }

    private fun fetchItems() {
        lifecycleScope.launch {
            (activity?.application as MusixsterApplication).db.songDao().getAllEntries().collect { databaseList ->
                databaseList.map { entity ->
                    SongEntry(
                        entity.title,
                        entity.artist
                    )
                }.also { mappedList ->
                    songs.clear()
                    songs.addAll(mappedList)
                    itemAdapter.notifyDataSetChanged()
                }

            }
        }
    }
}