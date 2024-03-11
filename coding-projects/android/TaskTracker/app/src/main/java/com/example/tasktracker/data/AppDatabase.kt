package com.example.tasktracker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tasktracker.data.model.Task
import java.util.concurrent.Executors

/**
 * Created by Gauri Gadkari on 1/23/24.
 * The Room database for this app
 */

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDAO() : TaskDAO

    companion object{

        private var db : AppDatabase? = null

        private const val NUMBER_OF_THREADS = 4
        val databaseQueryExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS)

        fun getDB(context: Context) : AppDatabase?{
            if (db == null){
                db = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "com.jk.room_kotlin_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return db
        }

    }



}