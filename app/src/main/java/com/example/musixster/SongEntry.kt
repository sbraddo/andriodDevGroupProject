package com.example.musixster

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "song_entries")
data class SongEntry(
    @PrimaryKey(autoGenerate = true)
    var title: String,
    var artist: String
)