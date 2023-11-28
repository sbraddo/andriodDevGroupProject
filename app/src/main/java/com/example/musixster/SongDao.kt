package com.example.musixster

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao

interface SongDao {
    @Insert
    fun insert(SongEntry: SongEntry): Long

    @Query("SELECT * FROM song_entries")
    fun getAllEntries(): Flow<List<SongEntry>>
}