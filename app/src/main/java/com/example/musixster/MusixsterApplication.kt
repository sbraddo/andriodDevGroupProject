package com.example.musixster

import android.app.Application

class MusixsterApplication : Application() {
    val db by lazy { SongDatabase.getDatabase(this) }
}