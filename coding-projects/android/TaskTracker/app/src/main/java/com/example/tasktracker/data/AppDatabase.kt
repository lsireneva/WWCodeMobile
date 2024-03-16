package com.example.tasktracker.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tasktracker.data.model.Task

/**
 * Created by Gauri Gadkari on 1/23/24.
 * The Room database for this app
 */

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDAO(): TaskDAO
}